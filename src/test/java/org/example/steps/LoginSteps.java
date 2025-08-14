package org.example.steps;

import net.serenitybdd.annotations.Step;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Step("Open the login page")
    public void openLoginPage() {
        loginPage.openAt("");
    }

    @Step("Login with username {0} and password {1}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Step("Verify that the products page is displayed")
    public void verifyProductsPageIsDisplayed() {
        assertTrue("Products page is not displayed!", productsPage.isProductsPageDisplayed());
    }

    @Step("Verify that the error message is displayed")
    public void verifyErrorMessageIsDisplayed() {
        assertTrue("Error message is not displayed!", loginPage.isErrorMessageDisplayed());
    }

    @Step("Logout from the application")
    public void logout() {
        productsPage.logout();
    }

    @Step("Verify that the login page is displayed")
    public void verifyLoginPageIsDisplayed() {
        assertTrue("Login page is not displayed!", loginPage.isLoginPageDisplayed());
    }
}