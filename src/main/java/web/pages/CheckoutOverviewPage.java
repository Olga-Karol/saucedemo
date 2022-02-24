package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;

import java.util.ArrayList;
import java.util.List;

import static web.pages.CartPage.PRODUCT_IN_CART;
import static web.pages.CartPage.PRODUCT_LOCATOR;

public class CheckoutOverviewPage extends BasePage {


    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Overview']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public CheckoutCompletePage clickFinishButton(){
        driver.findElement(By.cssSelector("#finish")).click();
        return new CheckoutCompletePage(driver);
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
    public CheckoutOverviewPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public CheckoutOverviewPage open() {
        driver.get(baseUrl);
        return this;
    }
}
