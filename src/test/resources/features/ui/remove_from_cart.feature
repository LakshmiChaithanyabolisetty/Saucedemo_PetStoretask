Feature: Remove Product from Cart

  @RemoveFromCart
  Scenario: Remove a product from the cart
    Given I am logged in and on the products page
    When I add a product to the cart
    And I remove the product from the cart
    Then the cart icon should display zero items