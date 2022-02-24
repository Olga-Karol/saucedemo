package web.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public abstract class BasePage {

    protected WebDriver driver;
    protected String baseUrl;
    protected By baseElementLocator;
    protected WebDriverWait explicitlyWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public abstract BasePage isPageLoaded();

    public abstract BasePage open();

    public String getPlaceholder(By fieldName) {
        return driver.findElement(fieldName).getAttribute("placeholder");
    }

    public String getElementText(By By) {
        String elementText = driver.findElement(By).getText();
        if (elementText == null || elementText.isEmpty()) {
            elementText = driver.findElement(By).getAttribute("value");
        }
        return elementText;
    }

}
