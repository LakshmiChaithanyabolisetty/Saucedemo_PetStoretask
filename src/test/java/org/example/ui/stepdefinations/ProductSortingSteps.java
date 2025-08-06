package org.example.ui.stepdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.configuration.DriverFactory;
import org.example.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ProductSortingSteps {
    private WebDriver driver;
    private ProductsPage productsPage;

    public ProductSortingSteps() {
        this.driver = DriverFactory.getDriver();
        this.productsPage = new ProductsPage(driver);
    }

    @When("I sort products by name in ascending order")
    public void iSortProductsByNameInAscendingOrder() {
        productsPage.sortProductsByNameAZ();
    }

    @Then("the products should be sorted alphabetically from A to Z")
    public void theProductsShouldBeSortedAlphabeticallyFromAToZ() {
        List<String> actualProductNames = productsPage.getProductNames();
        List<String> expectedProductNames = actualProductNames.stream().sorted().collect(Collectors.toList());
        assertEquals("The products are not sorted alphabetically from A to Z!", expectedProductNames, actualProductNames);
    }

    @When("I sort products by name in descending order")
    public void iSortProductsByNameInDescendingOrder() {
        productsPage.sortProductsByNameZA();
    }

    @Then("the products should be sorted alphabetically from Z to A")
    public void theProductsShouldBeSortedAlphabeticallyFromZToA() {
        List<String> actualProductNames = productsPage.getProductNames();
        List<String> expectedProductNames = actualProductNames.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        assertEquals("The products are not sorted alphabetically from Z to A!", expectedProductNames, actualProductNames);
    }

    @When("I sort products by price in ascending order")
    public void iSortProductsByPriceInAscendingOrder() {
        productsPage.sortProductsByPriceLowToHigh();
    }

    @Then("the products should be sorted by price from low to high")
    public void theProductsShouldBeSortedByPriceFromLowToHigh() {
        List<Double> actualProductPrices = productsPage.getProductPrices();
        List<Double> expectedProductPrices = actualProductPrices.stream().sorted().collect(Collectors.toList());
        assertEquals("The products are not sorted by price from low to high!", expectedProductPrices, actualProductPrices);
    }

    @When("I sort products by price in descending order")
    public void iSortProductsByPriceInDescendingOrder() {
        productsPage.sortProductsByPriceHighToLow();
    }

    @Then("the products should be sorted by price from high to low")
    public void theProductsShouldBeSortedByPriceFromHighToLow() {
        List<Double> actualProductPrices = productsPage.getProductPrices();
        List<Double> expectedProductPrices = actualProductPrices.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        assertEquals("The products are not sorted by price from high to low!", expectedProductPrices, actualProductPrices);
    }
}
