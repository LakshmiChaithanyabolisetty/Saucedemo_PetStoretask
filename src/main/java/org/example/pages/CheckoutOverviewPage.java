package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    private final WebDriverWait wait;

    @FindBy(css = ".cart_item")
    private WebElement cartItem;

    @FindBy(css = ".inventory_item_name")
    private WebElement productName;

    @FindBy(css = ".inventory_item_price")
    private WebElement productPrice;

    @FindBy(css = ".summary_subtotal_label")
    private WebElement subtotalPrice;

    @FindBy(css = ".summary_tax_label")
    private WebElement taxAmount;

    @FindBy(css = ".summary_total_label")
    private WebElement totalPrice;

    public CheckoutOverviewPage(WebDriver driver) {
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

    public String getSubtotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(subtotalPrice));
        return subtotalPrice.getText().replace("Item total: $", "");
    }

    public String getTaxAmount() {
        wait.until(ExpectedConditions.visibilityOf(taxAmount));
        return taxAmount.getText().replace("Tax: $", "");
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return totalPrice.getText().replace("Total: $", "");
    }
}
