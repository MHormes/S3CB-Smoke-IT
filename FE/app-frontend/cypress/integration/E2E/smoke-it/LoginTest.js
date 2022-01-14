const urlBE = '127.0.0.1:8080/';
const urlFE = '127.0.0.1:3000/';

describe("Login test", () => {
    it('successfull login admin', () => {
        //create admin account
        cy.request('POST', urlBE + 'user/register',
            {
                username: 'adminLogin',
                password: 'admin',
                email: 'admin@admin.nl',
                role: 'ADMIN'
            }).then(
                (response) => {
                    expect(response.body).to.have.property('username', 'adminLogin')
                })

        //spy on endpoint
        cy.intercept('/login').as('login')
        //perform login
        cy.visit(urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('adminLogin');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('admin');
        cy.get('.LoginPage_login_form__22R7M').submit();

        //check for adminlog on local storage and correct status code
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('true')
        })
        cy.wait('@login').its('response.statusCode').should('equal', 200);
    })

    it('successfull login user', () => {
        //create user account
        cy.request('POST', urlBE + 'user/register',
            {
                username: 'userLogin',
                password: 'user',
                email: 'user@user.nl'
            }).then(
                (response) => {
                    expect(response.body).to.have.property('username', 'userLogin')
                })

        //spy on endpoint
        cy.intercept('/login').as('login')
        //perform login
        cy.visit(urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('userLogin');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('user');
        cy.get('.LoginPage_login_form__22R7M').submit();

        //check for adminlog on local storage and correct status code
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal('false')
        })
        cy.wait('@login').its('response.statusCode').should('equal', 200);
    })

    it('failed login', () => {
        //spy on endpoint
        cy.intercept('POST', '/login').as('login')
        //No account created, perform login
        cy.visit(urlFE + 'login');
        cy.get('.LoginPage_row__2Yo4h:nth-child(1) input').type('admin');
        cy.get('.LoginPage_row__2Yo4h:nth-child(2) input').type('user');
        cy.get('.LoginPage_login_form__22R7M').submit();
        //Check if adminlog is null and forbidden error
        cy.should(() => {
            expect(localStorage.getItem('adminLog')).to.equal(null)
        })
        cy.wait('@login').its('response.statusCode').should('equal', 403);
    })
})