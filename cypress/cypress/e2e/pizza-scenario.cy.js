// Goal: QP-01 verifies that restrictions survive a pizza request, rating needs login,
// and the admin list shows the same tool for the new pizza.
function stamp() { const d = new Date(); const two = n => String(n).padStart(2, '0'); return d.getFullYear() + two(d.getMonth() + 1) + two(d.getDate()) + '-' + two(d.getHours()) + two(d.getMinutes()) + two(d.getSeconds()) }
describe('QP-01 A custom pizza reaches the admin list', () => {
  it('follows the steps of QP-01', () => {
    const pizzaName = 'Lab Pizza ' + stamp()
    cy.visit('https://quickpizza.grafana.com')
    cy.get('h1').should('have.text', 'Looking to break out of your pizza routine?')
    cy.contains('label', 'Advanced').click()
    ;['Max Calories per Slice', 'Excluded tools', 'Must be vegetarian', 'Custom Pizza Name'].forEach(label => cy.contains('label', label).should('be.visible'))
    cy.contains('label', 'Max Calories per Slice').siblings('input').clear().type('600').should('have.value', '600')
    cy.get('select').select('Knife'); cy.contains('option', 'Knife').should('be.selected')
    cy.get('#default-checkbox').check().should('be.checked')
    cy.get('input#pizza-name').type(pizzaName).should('have.value', pizzaName)
    cy.intercept('POST', '/api/pizza').as('pizza')
    cy.get('button[name="pizza-please"]').click()
    cy.wait('@pizza').its('response.statusCode').should('eq', 200)
    cy.get('@pizza').then(({ request, response }) => { expect(request.body.maxCaloriesPerSlice).to.equal(600); const { pizza, calories } = response.body; cy.contains('#recommendations p', 'Tool:').should('have.text', 'Tool: ' + pizza.tool); cy.contains('#recommendations p', 'Calories per slice:').should('have.text', 'Calories per slice: ' + calories); cy.wrap(pizza.tool).as('tool') })
    cy.get('#recommendations').should('contain', 'Name: ' + pizzaName)
    cy.contains('#recommendations p', 'Tool:').should('not.contain', 'Knife')
    cy.contains('#recommendations p', 'Calories per slice:').invoke('text').then(text => expect(Number(text.replace('Calories per slice:', ''))).to.be.at.most(600))
    cy.screenshot('qp01-step4-recommendation')
    cy.get('button[name="rate-5"]').click(); cy.get('#rate-result').should('have.text', 'Please log in first.'); cy.screenshot('qp01-step5-login-needed')
    cy.adminLogin('admin', 'admin'); cy.contains('Latest pizza recommendations').should('be.visible')
    cy.get('@tool').then(tool => cy.contains('li', pizzaName).should('contain', '(tool=' + tool + ','))
    cy.screenshot('qp01-step7-admin-list')
  })
})
