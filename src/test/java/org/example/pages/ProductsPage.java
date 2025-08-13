package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageObject{

    @FindBy(className = "inventory_item_name")
    private WebElement firstProductName;

    @FindBy(className = "btn_inventory")
    private WebElement firstProductAddButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartIcon;

    @FindBy(className = "btn_secondary")
    private WebElement firstProductRemoveButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(className = "title")
    private WebElement productsTitle;

    public void addFirstProductToCart() {
        firstProductAddButton.click();
    }

    public boolean isCartIconDisplayed() {
        return cartIcon.isDisplayed();
    }

    public String getCartItemCount() {
        return cartIcon.getText();
    }

    public void removeFirstProductFromCart() {
        firstProductRemoveButton.click();
    }

    public boolean isProductsPageDisplayed() {
        return productsTitle.isDisplayed();
    }

    public void logout() {
        logoutButton.click();
    }
}
