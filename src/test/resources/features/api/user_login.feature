Feature: User Login

  @LoginUser
  Scenario Outline: Verify user login with valid credentials
    Given I have valid login credentials with username "<username>" and password "<password>"
    When I send a login request
    Then the response status code for user login should be 200
    And the response should contain a valid session token

    Examples:
      | username | password    |
      | john_doe | password123 |
