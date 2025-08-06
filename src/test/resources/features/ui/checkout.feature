Feature: Checkout Process

  @CheckoutStep1
  Scenario Outline: Enter user information during checkout
    Given I am logged in and on the products page
    When I add a product to the cart
    And I navigate to the cart page
    And I proceed to the checkout page
    And I enter user information with first name "<firstName>", last name "<lastName>", and postal code "<postalCode>"
    Then I should be redirected to the checkout overview page
    Then I should see the correct order summary on the checkout overview page

    Examples:
      | firstName | lastName | postalCode |
      | John      | Doe      | 12345      |

  @MissingCheckoutInfo
  Scenario Outline: Verify error message for missing checkout info
    Given I am logged in and on the products page
    When I add a product to the cart
    And I navigate to the cart page
    And I proceed to the checkout page
    And I leave the "<field>" blank and click continue
    Then I should see the error message "<expectedErrorMessage>"

    Examples:
      | field       | expectedErrorMessage                          |
      | firstName   | Error: First Name is required                 |
