package org.example.ui.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.configuration.DriverFactory;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class AddToCartSteps {
    private WebDriver driver;
    private ProductsPage productsPage;

    public AddToCartSteps() {
        this.driver = DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @Given("I am logged in and on the products page")
    public void iAmLoggedInAndOnTheProductsPage() {
        assertEquals("The user is not on the products page!", "https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @When("I add a product to the cart")
    public void iAddAProductToTheCart() {
        this.productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
    }

    @Then("the cart icon should display the correct number of items")
    public void theCartIconShouldDisplayTheCorrectNumberOfItems() {
        int itemCount = productsPage.getCartItemCount();
        assertEquals("The cart icon does not display the correct number of items!", 1, itemCount);
    }

    @When("I click on the logout button")
    public void iClickOnTheLogoutButton() {
        productsPage.clickMenuButton();
        productsPage.clickLogoutButton();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        assertEquals("The user is not redirected to the login page!", "https://www.saucedemo.com/", driver.getCurrentUrl());
    }
}
