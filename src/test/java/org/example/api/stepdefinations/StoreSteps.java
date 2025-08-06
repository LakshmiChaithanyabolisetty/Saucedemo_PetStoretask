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

public class StoreSteps {
    private Response response;
    private Map<String, Object> orderDetails;
    private int orderId;

    @Given("I have order details with order ID {string}, pet ID {string}, quantity {string}, status {string}, and complete {string}")
    public void iHaveOrderDetailsWithOrderIDPetIDQuantityShipDateStatusAndComplete(String orderId, String petId, String quantity, String status, String complete) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        orderDetails = new HashMap<>();
        orderDetails.put("id", Integer.parseInt(orderId));
        orderDetails.put("petId", Integer.parseInt(petId));
        orderDetails.put("quantity", Integer.parseInt(quantity));
        orderDetails.put("status", status);
        orderDetails.put("complete", Boolean.parseBoolean(complete));
    }

    @When("I send a request to place the order")
    public void iSendARequestToPlaceTheOrder() {
        response = given()
                .header("Content-Type", "application/json")
                .body(orderDetails)
                .post("/store/order");
        response.then().log().all();
    }

    @Then("the response status code for placing order should be {int}")
    public void theResponseStatusCodeForPlacingOrderStatusShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the pet ID in the response for placing order should be {string}")
    public void thePetIDInTheResponseForPlacingOrderShouldBe(String expectedPetId) {
        int actualPetId = response.jsonPath().getInt("id");
        Assertions.assertEquals(Integer.parseInt(expectedPetId), actualPetId, "Pet ID mismatch!");
    }

    @Then("the order ID in the response should be {string}")
    public void theOrderIDInTheResponseShouldBe(String expectedOrderId) {
        int actualOrderId = response.jsonPath().getInt("id");
        Assertions.assertEquals(Integer.parseInt(expectedOrderId), actualOrderId, "Order ID mismatch!");
    }

    @Then("the order status in the response should be {string}")
    public void theOrderStatusInTheResponseShouldBe(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("status");
        Assertions.assertEquals(expectedStatus, actualStatus, "Order status mismatch!");
    }

    @Given("I have a valid order ID {string}")
    public void iHaveAValidOrderID(String orderId) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        this.orderId = Integer.parseInt(orderId);
    }

    @When("I send a request to get the order details")
    public void iSendARequestToGetTheOrderDetails() {
        response = given()
                .header("Content-Type", "application/json")
                .get("/store/order/" + orderId);
        response.then().log().all();
    }

    @Then("the quantity in the response should be {string}")
    public void theQuantityInTheResponseShouldBe(String expectedQuantity) {
        int actualQuantity = response.jsonPath().getInt("quantity");
        Assertions.assertEquals(Integer.parseInt(expectedQuantity), actualQuantity, "Quantity mismatch!");
    }

    @Then("the response status code for getting order by id should be {int}")
    public void theResponseStatusCodeForGettingOrderByIdShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }

    @Then("the pet ID in the response for fetching order by id should be {string}")
    public void thePetIDInTheResponseForFetchingOrderByIdShouldBe(String expectedPetId) {
        int actualPetId = response.jsonPath().getInt("id");
        Assertions.assertEquals(Integer.parseInt(expectedPetId), actualPetId, "Pet ID mismatch!");
    }

    @Then("the complete status in the response should be {string}")
    public void theCompleteStatusInTheResponseShouldBe(String expectedComplete) {
        boolean actualComplete = response.jsonPath().getBoolean("complete");
        Assertions.assertEquals(Boolean.parseBoolean(expectedComplete), actualComplete, "Complete status mismatch!");
    }

    @Given("I have access to the inventory endpoint")
    public void iHaveAccessToTheInventoryEndpoint() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @When("I send a request to get the inventory details")
    public void iSendARequestToGetTheInventoryDetails() {
        response = given()
                .header("Content-Type", "application/json")
                .get("/store/inventory");
        response.then().log().all();
    }

    @Then("the inventory should contain counts for statuses {string}, {string}, and {string}")
    public void theInventoryShouldContainCountsForStatuses(String status1, String status2, String status3) {
        Map<String, Integer> inventory = response.jsonPath().getMap("$");
        Assertions.assertTrue(inventory.containsKey(status1), "Inventory does not contain status: " + status1);
        Assertions.assertTrue(inventory.containsKey(status2), "Inventory does not contain status: " + status2);
        Assertions.assertTrue(inventory.containsKey(status3), "Inventory does not contain status: " + status3);
        System.out.println("Inventory counts: " + inventory);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode(), "Status code mismatch for adding the pet!");
    }
}
