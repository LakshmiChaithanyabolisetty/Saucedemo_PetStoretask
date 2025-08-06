Feature: Login Functionality

  @ValidLogin
  Scenario: Login with valid credentials
    Given I am on the login page
    When I enter valid credentials and click login
    Then I should be redirected to the products page

  @InvalidLogin
  Scenario: Login with invalid credentials
    Given I am on the login page
    When I enter invalid credentials and click login
    Then I should see an error message

  @Logout
  Scenario: Verify logout functionality
    Given I am logged in and on the products page
    When I click on the logout button
    Then I should be redirected to the login page