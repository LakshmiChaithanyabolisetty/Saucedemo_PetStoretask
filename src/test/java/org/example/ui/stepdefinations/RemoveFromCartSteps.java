package org.example.ui.stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.configuration.DriverFactory;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class RemoveFromCartSteps {
    private WebDriver driver;
    private ProductsPage productsPage;

    public RemoveFromCartSteps() {
        this.driver = DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @When("I remove the product from the cart")
    public void iRemoveTheProductFromTheCart() {
        productsPage.removeFirstProductFromCart();
    }

    @Then("the cart icon should display zero items")
    public void theCartIconShouldDisplayZeroItems() {
        int itemCount = productsPage.getCartItemCount();
        assertEquals("The cart icon does not display zero items!", 0, itemCount);
    }
}
