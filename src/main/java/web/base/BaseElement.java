package web.base;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BaseElement {

    protected WebDriver driver;
    protected By baseElementLocator;
    protected WebDriverWait explicitlyWaitElement;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
        this.explicitlyWaitElement = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public abstract BaseElement isElementDisplayed();

}
