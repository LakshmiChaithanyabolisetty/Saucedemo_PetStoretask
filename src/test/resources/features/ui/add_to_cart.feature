Feature: Add Product to Cart

  @AddToCart
  Scenario: Add a product to the cart
    Given I am logged in and on the products page
    When I add a product to the cart
    Then the cart icon should display the correct number of items