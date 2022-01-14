const urlBE = 'http://localhost:8080/';
const urlFE = 'http://localhost:3000/';

describe("Box CRUD test", () => {
    //execute once to create admin account
    before(() => {
        //create admin account
        cy.request('POST', urlBE + 'user/register',
            {
                username: 'adminBoxes',
                password: 'admin',
                email: 'admin@admin.nl',
                role: 'ADMIN'
            }).then(
                (response) => {
                    expect(response.body).to.have.property('username', 'adminBoxes')
                })
    })
    //execute before every task to ensure admin login
    beforeEach(() => {
        //spy on endpoint
        cy.intercept('/login').as('login')
        //perform login
        cy.visit(urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('adminBoxes');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('admin');
        cy.get('.LoginPage_login_form__22R7M').submit();
        //check for adminlog on local storage and correct status code
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('true')
        })
        cy.wait('@login').its('response.statusCode').should('equal', 200);
    })

    it('Add new box', () => {
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

    it('update existing box', () => {
        //spy on update endpoint
        cy.intercept('PUT', 'boxes/update').as('updateBox')
        //update box
        cy.visit(urlFE + 'boxes/');
        cy.get('[data-cy=update-button-boxes]').first().click()
        cy.fixture('roll-kit.png').then(fileContent => {
            cy.get('[data-cy=fileInput-update]').attachFile({
                fileContent: fileContent.toString(),
                fileName: 'roll=kit.png',
                mimeType: 'image.png'
            });
        })
        cy.get('label:nth-child(4) > input').type('Update');
        cy.get('form').submit();
        //Check response statuscode and body with updated box values
        cy.wait('@updateBox').its('response.statusCode').should('equal', 200)
        cy.get('@updateBox').its('response.body.name').should('equal', 'testBoxUpdate')
    })

    it('delete existing box', () => {
        //spy on delete endpoint
        cy.intercept('DELETE', 'boxes/delete/*').as('deleteBox')
        //remove box
        cy.visit(urlFE + 'boxes/');
        cy.get('[data-cy=delete-button-boxes]').first().click()
        cy.on('window:confirm', (text) => {
            expect(text).to.contains("Are you sure you wish to delete")
        })
        //check response statuscode and value in body
        cy.wait('@deleteBox').its('response.statusCode').should('equal', 200)
        cy.get('@deleteBox').its('response.body').should('equal', true)
    })
})