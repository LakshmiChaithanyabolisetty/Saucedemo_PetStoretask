package org.example.steps;

import net.serenitybdd.annotations.Step;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;

import static org.junit.Assert.assertTrue;

public class CartSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Step("Login with username and password")
    public void login(String username, String password) {
        loginPage.open();
        loginPage.login(username, password);
    }

    @Step("Verify that the products page is displayed")
    public void verifyProductsPageIsDisplayed() {
        assertTrue("Products page is not displayed!", productsPage.isCartIconDisplayed());
    }

    @Step("Add the first product to the cart")
    public void addFirstProductToCart() {
        productsPage.addFirstProductToCart();
    }

    @Step("Remove the first product from the cart")
    public void removeFirstProductFromCart() {
        productsPage.removeFirstProductFromCart();
    }

    @Step("Verify that the cart icon displays {0} items")
    public void verifyCartItemCount(String expectedItemCount) {
        String actualItemCount = productsPage.getCartItemCount();
        assertTrue("Expected " + expectedItemCount + " items, but got " + actualItemCount,
                actualItemCount.equals(expectedItemCount));
    }
}