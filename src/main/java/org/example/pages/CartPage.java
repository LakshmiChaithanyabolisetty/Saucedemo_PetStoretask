package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriverWait wait;

    @FindBy(css = ".cart_item")
    private WebElement cartItem;

    @FindBy(css = ".inventory_item_name")
    private WebElement productName;

    @FindBy(css = ".inventory_item_price")
    private WebElement productPrice;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(cartItem));
        return productName.getText();
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(cartItem));
        return productPrice.getText();
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}
