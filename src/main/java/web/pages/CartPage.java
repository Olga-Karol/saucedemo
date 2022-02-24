package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/cart.html";

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Your Cart']");
    public static final By PRODUCT_IN_CART = By.xpath("//div[@class='cart_item']");
    public static final By PRODUCT_LOCATOR = By.xpath(".//div[@class='inventory_item_name']");
    private static final String REMOVE_BUTTON_PATTERN = "//div[contains(text(),'%s')]/ancestor::div[@class='cart_item']//button";
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.id("checkout");


    public CartPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public CheckoutYourInformationPage clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutYourInformationPage(driver);
    }

    public CatalogPage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new CatalogPage(driver);
    }

    public CartPage removeProduct(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON_PATTERN, partialProductTitle))).click();
        return this;
    }

    public boolean isCartEmpty() {
        List<WebElement> products = driver.findElements(PRODUCT_IN_CART);
        return products.isEmpty();
    }


    public List<String> areProductAdded(List<String> partialTitles) {
        List<WebElement> products = driver.findElements(PRODUCT_IN_CART);
        if (products.isEmpty()) {
            return null;
        }
        List<String> productsList = new ArrayList<>();
        for (WebElement product : products) {
            String productTitle = product.findElement(PRODUCT_LOCATOR).getText();
            if (!partialTitles.contains(productTitle)) {
                return null;
            } else {
                productsList.add(productTitle);
            }
        }
        return productsList;
    }

    @Override
    public CartPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public CartPage open() {
        driver.get(baseUrl);
        return this;
    }
}
