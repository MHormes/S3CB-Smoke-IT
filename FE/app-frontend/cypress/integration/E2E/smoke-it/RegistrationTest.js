const urlBE = 'http://localhost:8080/';
const urlFE = 'http://localhost:3000/';

describe("Registration test", () => {
    it('successfull registration for user', () => {
        //spy on endpoint
        cy.intercept('POST', 'user/register').as('register')
        //create account
        cy.visit(urlFE + 'register');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(1) input').type('userRegister');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(2) input').type('user');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(3) input').type('test@user.nl');
        cy.get('.RegistrationPage_register_form__MLqnw').submit();
        //check for correct status code and response body
        cy.wait('@register').its('response.statusCode').should('equal', 200)
        cy.get('@register').its('response.body.username').should('equal', 'userRegister')
    })
    it('failed registration for user (same username)', () => {
        //spy on endpoint
        cy.intercept('POST', 'user/register').as('register')
        //create account
        cy.visit(urlFE + 'register');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(1) input').type('userRegister');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(2) input').type('user');
        cy.get('.RegistrationPage_row__2HoV4:nth-child(3) input').type('test@user.nl');
        cy.get('.RegistrationPage_register_form__MLqnw').submit();
        //check for correct status code (409 for conflict) and response body
        cy.wait('@register').its('response.statusCode').should('equal', 409)
        cy.get('@register').its('response.body').should('include', 'is already taken')
    })
})