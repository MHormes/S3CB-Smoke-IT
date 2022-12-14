const urlBE = 'http://127.0.0.1:8080/';
const urlFE = 'http://127.0.0.1:3000/';

describe("Registration test", () => {
    it('successfull registration for user', () => {
        //spy on endpoint
        cy.intercept('POST', urlBE + 'user/register').as('register')
        //create account
        cy.visit(urlFE + 'register');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(1) input').type('userRegister');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(2) input').type('user');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(3) input').type('test@user.nl');
        cy.get('.RegistrationPage_register_form__3ik66').submit();
        //check for correct status code and response body
        cy.wait('@register').its('response.statusCode').should('equal', 200)
        cy.get('@register').its('response.body.username').should('equal', 'userRegister')
    })
    it('failed registration for user (same username)', () => {
        //spy on endpoint
        cy.intercept('POST', urlBE + 'user/register').as('register')
        //create account
        cy.visit(urlFE + 'register');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(1) input').type('userRegister');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(2) input').type('user');
        cy.get('.RegistrationPage_row__1WOa4:nth-child(3) input').type('test@user.nl');
        cy.get('.RegistrationPage_register_form__3ik66').submit();
        //check for correct status code (409 for conflict) and response body
        cy.wait('@register').its('response.statusCode').should('equal', 409)
        cy.get('@register').its('response.body').should('include', 'is already taken')
    })
})