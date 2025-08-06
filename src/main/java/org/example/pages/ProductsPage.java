package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {
    private final WebDriverWait wait;

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    private WebElement firstProductAddToCartButton;

    @FindBy(css = ".inventory_item:nth-child(1) .btn_inventory")
    private WebElement firstProductRemoveButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartItemCountBadge;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".product_sort_container")
    private WebElement sortingDropdown;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    public ProductsPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductAddToCartButton));
        firstProductAddToCartButton.click();
    }

    public void removeFirstProductFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductRemoveButton));
        firstProductRemoveButton.click();
    }

    public int getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartItemCountBadge));
            String itemCountText = cartItemCountBadge.getText();
            return Integer.parseInt(itemCountText);
        } catch (Exception e) {
            return 0;
        }
    }

    public void navigateToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
    }

    public void sortProductsByNameAZ() {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropdown));
        Select dropdown = new Select(sortingDropdown);
        dropdown.selectByVisibleText("Name (A to Z)");
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void sortProductsByNameZA() {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropdown));
        Select dropdown = new Select(sortingDropdown);
        dropdown.selectByVisibleText("Name (Z to A)");
    }

    public void sortProductsByPriceLowToHigh() {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropdown));
        Select dropdown = new Select(sortingDropdown);
        dropdown.selectByVisibleText("Price (low to high)");
    }

    public void sortProductsByPriceHighToLow() {
        wait.until(ExpectedConditions.elementToBeClickable(sortingDropdown));
        Select dropdown = new Select(sortingDropdown);
        dropdown.selectByVisibleText("Price (high to low)");
    }

    public List<Double> getProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        return productPrices.stream()
                .map(price -> Double.parseDouble(price.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}
