describe('My First Test', () => {
    it('find the content "type"', () => {
      cy.visit("https://example.cypress.io")

      cy.contains('type').click()

      cy.url().should('include', '/commands/actions')

      cy.get('.action-email')
      .type("mymail@gmail.com")
      .should('have.value', "mymail@gmail.com")
    })
  })