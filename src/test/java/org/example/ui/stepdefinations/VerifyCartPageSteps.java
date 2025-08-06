package org.example.ui.stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.configuration.DriverFactory;
import org.example.pages.CartPage;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class VerifyCartPageSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;

    public VerifyCartPageSteps() {
        this.driver = DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
    }

    @When("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
        productsPage.navigateToCartPage();
    }

    @Then("I should see the correct product details in the cart")
    public void iShouldSeeTheCorrectProductDetailsInTheCart() {
        String expectedProductName = "Sauce Labs Backpack";
        String expectedProductPrice = "$29.99";

        String actualProductName = cartPage.getProductName();
        String actualProductPrice = cartPage.getProductPrice();

        assertEquals("Product name in the cart is incorrect!", expectedProductName, actualProductName);
        assertEquals("Product price in the cart is incorrect!", expectedProductPrice, actualProductPrice);
    }
}
