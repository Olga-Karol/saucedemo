package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BaseElement;

public class CartElement extends BaseElement {

    public static final By CART_ICON = By.id("shopping_cart_container");
    public static final By COUNTER_ICON = By.className("shopping_cart_badge");


    public CartElement(WebDriver driver) {
        super(driver);
        this.baseElementLocator = CART_ICON;
    }

    @Override
    public CartElement isElementDisplayed() {
        try {
            explicitlyWaitElement.until(ExpectedConditions.visibilityOfElementLocated(CART_ICON));
        } catch (TimeoutException ex) {
            return null;
        }
        return this;
    }

    public By isCountDisplayed() {
        try {
            explicitlyWaitElement.until(ExpectedConditions.visibilityOfElementLocated(COUNTER_ICON));
        } catch (TimeoutException ex) {
            return null;
        }
        return COUNTER_ICON;
    }

    public void clickCartIcon() {
        driver.findElement(CART_ICON).click();
    }

    public int getCountValue() {
        int quantityAddedProducts = Integer.valueOf((driver.findElement(COUNTER_ICON).getText()));
        return quantityAddedProducts;
    }
}
