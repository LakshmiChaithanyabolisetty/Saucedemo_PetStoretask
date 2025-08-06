package org.example.api.stepdefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserSteps {
    private Response response;
    private Map<String, Object> userDetails;

    @Given("I have user details with id {string}, username {string}, first name {string}, last name {string}, email {string}, password {string}, phone {string}, and user status {string}")
    public void iHaveUserDetailsWithIdUsernameFirstNameLastNameEmailPasswordPhoneAndUserStatus(
            String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        userDetails = new HashMap<>();
        userDetails.put("id", Integer.parseInt(id));
        userDetails.put("username", username);
        userDetails.put("firstName", firstName);
        userDetails.put("lastName", lastName);
        userDetails.put("email", email);
        userDetails.put("password", password);
        userDetails.put("phone", phone);
        userDetails.put("userStatus", Integer.parseInt(userStatus));
    }

    @When("I send a request to create the user")
    public void iSendARequestToCreateTheUser() {
        response = given()
                .header("Content-Type", "application/json")
                .body(userDetails)
                .post("/user");
        response.then().log().all();
    }

    @Then("the user creation response status code should be {int}")
    public void theUserCreationResponseStatusCodeShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "User creation status code mismatch!");
    }

    @Then("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        Assertions.assertEquals(expectedMessage, actualMessage, "Response message mismatch!");
    }

//    @Given("I have invalid login credentials with username {string} and password {string}")
//    public void iHaveInvalidLoginCredentialsWithUsernameAndPassword(String username, String password) {
//        RestAssured.baseURI = "https://petstore.swagger.io/v2";
//        this.username = username;
//        this.password = password;
//    }

    @Then("the response should contain an error message {string}")
    public void theResponseShouldContainAnErrorMessage(String expectedErrorMessage) {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains(expectedErrorMessage),
                "Expected error message not found in the response! Actual response: " + responseBody);
    }
}
