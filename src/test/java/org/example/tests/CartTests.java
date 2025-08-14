package org.example.tests;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;

import org.example.steps.CartSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class CartTests {

    @Managed(driver = "chrome")
    WebDriver browser;

    @Steps
    CartSteps cartSteps;

    @Test
    public void addProductToCartTest() {
        cartSteps.login("standard_user", "secret_sauce");
        cartSteps.verifyProductsPageIsDisplayed();
        cartSteps.addFirstProductToCart();
        cartSteps.verifyCartItemCount("1");
    }

    @Test
    public void removeProductFromCartTest() {
        cartSteps.login("standard_user", "secret_sauce");
        cartSteps.verifyProductsPageIsDisplayed();
        cartSteps.addFirstProductToCart();
        cartSteps.removeFirstProductFromCart();
        cartSteps.verifyCartItemCount("0");
    }
}