package org.example.api.stepdefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;

public class LoginSteps {
    private Response response;
    private String username;
    private String password;

    @Given("I have valid login credentials with username {string} and password {string}")
    public void iHaveValidLoginCredentialsWithUsernameAndPassword(String username, String password) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.username = username;
        this.password = password;
    }

    @When("I send a login request")
    public void iSendALoginRequest() {
        response = given()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
        response.then().log().all();
    }

    @Then("the response status code for user login should be {int}")
    public void theResponseStatusCodeForUserLoginShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch!");
    }

    @Then("the response should contain a valid session token")
    public void theResponseShouldContainAValidSessionToken() {
        String responseBody = response.getBody().asString();
        Assertions.assertTrue(responseBody.contains("logged in user session:"), "Session token not found in the response!");
    }
}
