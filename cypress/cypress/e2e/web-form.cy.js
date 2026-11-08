// Goal: fill in every kind of field on Selenium's web form and check the result.
describe('Web form', () => {
  it('fills in the fields and sends the form', () => {
    cy.visit('https://www.selenium.dev/selenium/web/web-form.html')
    cy.title().should('eq', 'Web form')
    cy.get('#my-text-id').type('Cypress').should('have.value', 'Cypress')
    cy.get('input[name="my-password"]').type('Secret123')
    cy.get('textarea[name="my-textarea"]').type('Hello from Cypress')
    cy.get('select[name="my-select"]').select('Two').should('have.value', '2')
    cy.get('#my-check-2').check().should('be.checked')
    cy.get('#my-radio-2').check()
    cy.get('#my-radio-1').should('not.be.checked')
    cy.get('button[type="submit"]').click()
    cy.title().should('eq', 'Web form - target page')
    cy.get('#message').should('have.text', 'Received!')
  })
})
