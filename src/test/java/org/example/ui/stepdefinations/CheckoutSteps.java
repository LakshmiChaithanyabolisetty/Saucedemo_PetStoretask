package org.example.ui.stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.CartPage;
import org.example.pages.CheckoutOverviewPage;
import org.example.pages.CheckoutPage;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutSteps {
    private WebDriver driver;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    public CheckoutSteps() {
        this.driver = org.example.configuration.DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @When("I proceed to the checkout page")
    public void iProceedToTheCheckoutPage() {
        cartPage.clickCheckoutButton();
    }

    @When("I enter user information with first name {string}, last name {string}, and postal code {string}")
    public void iEnterUserInformation(String firstName, String lastName, String postalCode) {
        checkoutPage.enterUserInformation(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
    }

    @Then("I should be redirected to the checkout overview page")
    public void iShouldBeRedirectedToTheCheckoutOverviewPage() {
        assertTrue("The user is not on the checkout overview page!", driver.getCurrentUrl().contains("checkout-step-two.html"));
    }

    @Then("I should see the correct order summary on the checkout overview page")
    public void iShouldSeeTheCorrectOrderSummaryOnTheCheckoutOverviewPage() {
        // Expected values
        String expectedProductName = "Sauce Labs Backpack";
        String expectedProductPrice = "29.99"; // Without the "$" symbol
        double expectedTaxRate = 0.08; // Example tax rate: 8%

        // Actual values from the checkout overview page
        String actualProductName = checkoutOverviewPage.getProductName();
        String actualProductPrice = checkoutOverviewPage.getProductPrice().replace("$", ""); // Remove "$" symbol
        String actualSubtotal = checkoutOverviewPage.getSubtotalPrice();
        String actualTax = checkoutOverviewPage.getTaxAmount();
        String actualTotal = checkoutOverviewPage.getTotalPrice();

        // Validate product name and price
        assertEquals("Product name is incorrect!", expectedProductName, actualProductName);
        assertEquals("Product price is incorrect!", expectedProductPrice, actualProductPrice);

        // Calculate expected subtotal, tax, and total
        double expectedSubtotal = Double.parseDouble(expectedProductPrice);
        double expectedTax = expectedSubtotal * expectedTaxRate;
        double expectedTotal = expectedSubtotal + expectedTax;

        // Validate subtotal, tax, and total
        assertEquals("Subtotal is incorrect!", String.format("%.2f", expectedSubtotal), actualSubtotal);
        assertEquals("Tax amount is incorrect!", String.format("%.2f", expectedTax), actualTax);
        assertEquals("Total price is incorrect!", String.format("%.2f", expectedTotal), actualTotal);
    }


    @When("I leave the {string} blank and click continue")
    public void iLeaveTheFieldBlankAndClickContinue(String field) {
        String firstName = field.equals("firstName") ? null : "John";
        String lastName = field.equals("lastName") ? null : "Doe";
        String postalCode = field.equals("postalCode") ? null : "12345";

        checkoutPage.enterUserInformation(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = checkoutPage.getErrorMessage();
        assertEquals("The error message is incorrect!", expectedErrorMessage, actualErrorMessage);
    }
}
