package org.example.tests;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;

import org.example.steps.LoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class LoginTests {

    @Managed()
    WebDriver browser;

    @Steps
    LoginSteps loginSteps;

    @Test
    public void validLoginTest() {
        loginSteps.openLoginPage();
        loginSteps.login("standard_user", "secret_sauce");
        loginSteps.verifyProductsPageIsDisplayed();
    }

    @Test
    public void invalidLoginTest() {
        loginSteps.openLoginPage();
        loginSteps.login("standard_user", "password");
        loginSteps.verifyErrorMessageIsDisplayed();
    }

    @Test
    public void logoutTest() {
        loginSteps.openLoginPage();
        loginSteps.login("standard_user", "secret_sauce");
        loginSteps.verifyProductsPageIsDisplayed();
        loginSteps.logout();
        loginSteps.verifyLoginPageIsDisplayed();
    }
}
