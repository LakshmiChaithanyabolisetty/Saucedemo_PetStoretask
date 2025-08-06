package org.example.ui.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.configuration.DriverFactory;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://www.saucedemo.com");
    }

    @When("I enter valid credentials and click login")
    public void iEnterValidCredentialsAndClickLogin() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("I enter invalid credentials and click login")
    public void iEnterInvalidCredentialsAndClickLogin() {
        loginPage.login("invalid_user", "invalid_password");
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        assertTrue("The user is not redirected to the products page!", driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        assertTrue("The error message is not displayed!", loginPage.isErrorMessageDisplayed());
    }
}
