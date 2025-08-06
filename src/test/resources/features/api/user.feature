Feature: User Endpoints

  @CreateUser
  Scenario Outline: Create a new user
    Given I have user details with id "<id>", username "<username>", first name "<firstName>", last name "<lastName>", email "<email>", password "<password>", phone "<phone>", and user status "<userStatus>"
    When I send a request to create the user
    Then the user creation response status code should be 200
    And the response message should be "<id>"

    Examples:
      | id    | username | firstName | lastName | email                | password    | phone      | userStatus |
      | 12345 | john_doe | John      | Doe      | john.doe@example.com | password123 | 1234567890 | 1          |