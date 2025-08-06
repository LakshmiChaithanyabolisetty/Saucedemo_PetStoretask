Feature: Verify Cart Page

  @VerifyCartPage
  Scenario: Verify product details in the cart
    Given I am logged in and on the products page
    When I add a product to the cart
    And I navigate to the cart page
    Then I should see the correct product details in the cart