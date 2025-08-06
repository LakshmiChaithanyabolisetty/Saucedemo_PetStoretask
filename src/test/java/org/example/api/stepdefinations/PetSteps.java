package org.example.api.stepdefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class PetSteps {
    private Response response;
    private Map<String, Object> petDetails;
    private int petId;
    private String status;

    @Given("I have pet details with id {string}, name {string}, and status {string}")
    public void iHavePetDetailsWithIdNameAndStatus(String id, String name, String status) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        petDetails = new HashMap<>();
        petDetails.put("id", Integer.parseInt(id));
        petDetails.put("name", name);
        petDetails.put("status", status);
    }

    @When("I send a request to add the pet")
    public void iSendARequestToAddThePet() {
        response = given()
                .header("Content-Type", "application/json")
                .body(petDetails)
                .post("/pet");
        response.then().log().all();
    }

    @Then("the response status code for adding the pet should be {int}")
    public void theResponseStatusCodeForAddingThePetShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the pet name in the response should be {string}")
    public void thePetNameInTheResponseShouldBe(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assertions.assertEquals(expectedName, actualName, "Pet name mismatch!");
    }

    @Then("the pet status in the response should be {string}")
    public void thePetStatusInTheResponseShouldBe(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("status");
        Assertions.assertEquals(expectedStatus, actualStatus, "Pet status mismatch!");
    }

    @Given("I have a valid pet ID {string}")
    public void iHaveAValidPetID(String petId) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.petId = Integer.parseInt(petId);
    }

    @When("I send a request to get the pet details")
    public void iSendARequestToGetThePetDetails() {
        response = given()
                .header("Content-Type", "application/json")
                .get("/pet/" + petId);

        response.then().log().all();
    }

    @Then("the response status code for fetching pet by id should be {int}")
    public void theResponseStatusCodeForFetchingPetByIdShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the pet ID in the response should be {string}")
    public void thePetIDInTheResponseShouldBe(String expectedPetId) {
        int actualPetId = response.jsonPath().getInt("id");
        Assertions.assertEquals(Integer.parseInt(expectedPetId), actualPetId, "Pet ID mismatch!");
    }

    @Given("I have existing pet details with id {string}, name {string}, and status {string}")
    public void iHaveExistingPetDetailsWithIdNameAndStatus(String id, String name, String status) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        petDetails = new HashMap<>();
        petDetails.put("id", Integer.parseInt(id));
        petDetails.put("name", name);
        petDetails.put("status", status);
    }

    @When("I send a request to update the pet details")
    public void iSendARequestToUpdateThePetDetails() {
        response = given()
                .header("Content-Type", "application/json")
                .body(petDetails)
                .put("/pet");
        response.then().log().all();
    }

    @Then("the response status code for updating pet should be {int}")
    public void theResponseStatusCodeForUpdatingPetShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the updated pet name in the response should be {string}")
    public void theUpdatedPetNameInTheResponseShouldBe(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assertions.assertEquals(expectedName, actualName, "Pet name mismatch!");
    }

    @Then("the updated pet status in the response should be {string}")
    public void theUpdatedPetStatusInTheResponseShouldBe(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("status");
        Assertions.assertEquals(expectedStatus, actualStatus, "Pet status mismatch!");
    }

    @When("I send a request to delete the pet")
    public void iSendARequestToDeleteThePet() {
        response = given()
                .header("Content-Type", "application/json")
                .delete("/pet/" + petId);
        response.then().log().all();
    }

    @Then("the response status code for deleting pet should be {int}")
    public void theResponseStatusCodeForDeletingPetShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the response message should confirm pet deletion for ID {string}")
    public void theResponseMessageShouldConfirmPetDeletionForID(String expectedPetId) {
        String actualMessage = response.jsonPath().getString("message");
        Assertions.assertEquals(expectedPetId, actualMessage, "Response message mismatch!");
    }

    @Given("I have a valid pet status {string}")
    public void iHaveAValidPetStatus(String status) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.status = status;
    }

    @When("I send a request to find pets by status")
    public void iSendARequestToFindPetsByStatus() {
        response = given()
                .queryParam("status", status)
                .get("/pet/findByStatus");
        response.then().log().all();
    }

    @Then("the response status code for finding pet by status should be {int}")
    public void theResponseStatusCodeForFindingPetByIdStatusShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("all pets in the response should have the status {string}")
    public void allPetsInTheResponseShouldHaveTheStatus(String expectedStatus) {
        List<String> statuses = response.jsonPath().getList("status");
        Assertions.assertTrue(
                statuses.stream().allMatch(status -> status.equals(expectedStatus)),
                "Not all pets have the expected status!"
        );
    }

    @Given("I have an invalid or nonexistent pet ID {string}")
    public void iHaveAnInvalidOrNonexistentPetID(String petId) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.petId = Integer.parseInt(petId);
    }

    @Then("the error message in the response should indicate {string}")
    public void theErrorMessageInTheResponseShouldIndicate(String expectedErrorMessage) {
        String actualErrorMessage = response.jsonPath().getString("message");
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message mismatch!");
    }
}
