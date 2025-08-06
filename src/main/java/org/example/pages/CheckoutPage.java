package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessageContainer;


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterUserInformation(String firstName, String lastName, String postalCode) {
        if (firstName != null) {
            wait.until(ExpectedConditions.visibilityOf(firstNameInput));
            firstNameInput.clear();
            firstNameInput.sendKeys(firstName);
        }

        if (lastName != null) {
            wait.until(ExpectedConditions.visibilityOf(lastNameInput));
            lastNameInput.clear();
            lastNameInput.sendKeys(lastName);
        }

        if (postalCode != null) {
            wait.until(ExpectedConditions.visibilityOf(postalCodeInput));
            postalCodeInput.clear();
            postalCodeInput.sendKeys(postalCode);
        }
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
        return errorMessageContainer.getText();
    }
}
