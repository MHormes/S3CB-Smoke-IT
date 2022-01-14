const urlBE = '127.0.0.1:8080/';
const urlFE = '127.0.0.1:3000/';

describe('Order test', () => {
   // setup an admin account -> login -> create box to order
    before(() => {
        //create admin account
        cy.request('POST', urlBE + 'user/register',
            {
                username: 'adminOrder',
                password: 'admin',
                email: 'admin@admin.nl',
                role: 'ADMIN'
            }).then(
                (response) => {
                    expect(response.body).to.have.property('username', 'adminOrder')
                })

        //spy on login endpoint
        cy.intercept('/login').as('login')
        //perform login
        cy.visit(urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('adminOrder');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('admin');
        cy.get('.LoginPage_login_form__22R7M').submit();
        //check for adminlog on local storage and correct status code
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('true')
        })
        cy.wait('@login').its('response.statusCode').should('equal', 200);

        //create a box to order
        //spy on create endpoint
        cy.intercept('POST', 'boxes/create').as('addBox')
        //create box
        cy.visit(urlFE + 'boxes/');
        cy.get('[data-cy=add-button-boxes]').click()
        cy.fixture('roll-kit.png').then(fileContent => {
            cy.get('[data-cy=fileInput-add]').attachFile({
                fileContent: fileContent.toString(),
                fileName: 'roll=kit.png',
                mimeType: 'image.png'
            });
        })
        cy.get('label:nth-child(4) > input').type('testBox');
        cy.get('label:nth-child(6) > input').type('10');
        cy.get('label:nth-child(8) > input').type('something, something else, another thing');
        cy.get('label:nth-child(10) > input').type('just some text');
        cy.get('form').submit();
        cy.wait('@addBox').its('response.statusCode').should('equal', 200)
        cy.get('@addBox').its('response.body.name').should('equal', 'testBox')
    })

    it('create new order', () => {
        //spy on end point
        cy.intercept('POST', 'subscriptions/create').as('placeOrder')
        cy.visit(urlFE + 'boxes');
        //wait for boxes to get loaded
        cy.wait(2000)
        //select boxes and create order
        cy.get('[data-cy=box-list-item]').first().click();
        cy.get('.SelectedBoxPrice_buttonFirst__3IHHT:nth-child(9)').click();
        cy.get('.SelectedBoxPrice_buttonFirst__3IHHT:nth-child(22)').click();
        cy.get('[data-cy=continue-button-order]').click();
        cy.get('[data-cy=name-order]').type('Maarten Hormes');
        cy.get('[data-cy=email-order]').type('maarten.hormes@gmail.com');
        cy.get('[data-cy=address-order]').type('rachelsmolen 10');
        cy.get('[data-cy=postal-order]').type('1234AB');
        cy.get('[data-cy=city-order]').type('Eindhoven');
        cy.get('[data-cy=pay-button-order]').click();
        cy.get('form').submit();

        //check response items
        cy.wait('@placeOrder').its('response.statusCode').should('equal', 200);
        cy.get('@placeOrder').its('response.body.name').should('equal', 'Maarten Hormes')
    })

    it('create new order w/ login redirect', () => {
        //spy on end point
        cy.intercept('POST', 'subscriptions/create').as('placeOrder')
        cy.visit(urlFE + 'boxes');
        //wait for boxes to get loaded
        cy.wait(2000)
        //select boxes and create order
        cy.get('[data-cy=box-list-item]').first().click();
        cy.get('.SelectedBoxPrice_buttonFirst__3IHHT:nth-child(9)').click();
        cy.get('.SelectedBoxPrice_buttonFirst__3IHHT:nth-child(22)').click();
        cy.get('[data-cy=continue-button-order]').click();

        //go to login
        cy.get('[data-cy=login-button-checkout]').click();
        //login
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('adminOrder');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('admin');
        cy.get('.LoginPage_login_form__22R7M').submit();
        //check if redirect back worked
        cy.url().should('equal', urlFE + 'boxes/selectedBox/checkout')
        //check for adminlog on local storage
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('true')
        })
        //continue with checkout
        cy.get('[data-cy=name-order]').type('Maarten Hormes');
        cy.get('[data-cy=email-order]').type('maarten.hormes@gmail.com');
        cy.get('[data-cy=address-order]').type('rachelsmolen 10');
        cy.get('[data-cy=postal-order]').type('1234AB');
        cy.get('[data-cy=city-order]').type('Eindhoven');
        cy.get('[data-cy=pay-button-order]').click();
        cy.get('form').submit();

        //check response items
        cy.wait('@placeOrder').its('response.statusCode').should('equal', 200);
        cy.get('@placeOrder').its('response.body.name').should('equal', 'Maarten Hormes')
    })

    it('pack/send order', () =>{
        //spy on endpoint
        cy.intercept('/login').as('login')
        //perform login
        cy.visit( urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('adminOrder');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('admin');
        cy.get('.LoginPage_login_form__22R7M').submit();

        //check for adminlog on local storage and correct status code
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('true')
        })
        cy.wait('@login').its('response.statusCode').should('equal', 200);
        
        //spy on endpoint
        cy.intercept('PUT', 'subscriptions/orders/send/*').as('sendOrder')
        cy.intercept('PUT', 'subscriptions/orders/pack/*').as('packOrder')
        cy.visit(urlFE + 'adminPortal');
        cy.get('[data-cy=grouped-boxes]').first().click();
        cy.get('[data-cy=order-item]').first().click();
        cy.get('[data-cy=pack-button-admin]').click();

        //check response statuscode and value in body
        cy.wait('@packOrder').its('response.statusCode').should('equal', 200)
        cy.get('@packOrder').its('response.body.packed').should('equal', true)

        cy.get('[data-cy=ship-button-admin]').click();
        cy.on('window:confirm', (text) => {
            expect(text).to.contains("Are you sure you wish to send this order")
        })
        //check response statuscode and value in body
        cy.wait('@sendOrder').its('response.statusCode').should('equal', 200)
        cy.get('@sendOrder').its('response.body.shipped').should('equal', true)
    })
})