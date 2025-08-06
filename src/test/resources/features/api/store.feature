Feature: Store Endpoints

  @PlaceOrder
  Scenario Outline: Place an order for a pet
    Given I have order details with order ID "<orderId>", pet ID "<petId>", quantity "<quantity>", status "<status>", and complete "<complete>"
    When I send a request to place the order
    Then the response status code for placing order should be 200
    And the order ID in the response should be "<orderId>"
    And the pet ID in the response for placing order should be "<petId>"
    And the order status in the response should be "<status>"

    Examples:
      | orderId | petId | quantity | status | complete |
      | 1       | 1     | 2        | placed | true     |

  @GetInventory
  Scenario: Verify inventory details
    Given I have access to the inventory endpoint
    When I send a request to get the inventory details
    Then the response status code should be 200
    And the inventory should contain counts for statuses "available", "pending", and "sold"