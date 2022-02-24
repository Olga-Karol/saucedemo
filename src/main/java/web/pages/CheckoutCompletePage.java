package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;

public class CheckoutCompletePage extends BasePage {

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Complete!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        this.baseElementLocator = TITLE_LOCATOR;
    }

    @Override
    public CheckoutCompletePage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public CheckoutCompletePage open() {
        return null;
    }

}
