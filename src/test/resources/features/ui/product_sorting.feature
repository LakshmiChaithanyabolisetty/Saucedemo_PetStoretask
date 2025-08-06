Feature: Product Sorting

  @SortByNameAZ
  Scenario: Verify product sorting by name (A-Z)
    Given I am logged in and on the products page
    When I sort products by name in ascending order
    Then the products should be sorted alphabetically from A to Z

  @SortByNameZA
  Scenario: Verify product sorting by name (Z-A)
    Given I am logged in and on the products page
    When I sort products by name in descending order
    Then the products should be sorted alphabetically from Z to A

  @SortByPriceLowToHigh
  Scenario: Verify product sorting by price (Low to High)
    Given I am logged in and on the products page
    When I sort products by price in ascending order
    Then the products should be sorted by price from low to high

  @SortByPriceHighToLow
  Scenario: Verify product sorting by price (High to Low)
    Given I am logged in and on the products page
    When I sort products by price in descending order
    Then the products should be sorted by price from high to low