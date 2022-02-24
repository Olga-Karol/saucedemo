package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;

public class CheckoutYourInformationPage extends BasePage {

    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Checkout: Your Information']");

    public static final By FIRST_NAME_TEXT_FIELD = By.id("first-name");
    public static final By LAST_NAME_TEXT_FIELD = By.id("last-name");
    public static final By POSTAL_CODE_TEXT_FIELD = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    public static final String FIRST_NAME = "Tom";
    public static final String LAST_NAME = "Tomson";
    public static final String ZIP_CODE = "22222555";
    public static final String EMPTY_FIRST_NAME_TEXT = "Error: First Name is required";
    public static final String EMPTY_LAST_NAME_TEXT = "Error: Last Name is required";
    public static final String EMPTY_POSTAL_CODE_TEXT = "Error: Postal Code is required";
    public static final String FIRST_NAME_TEXT_FIELD_PLACEHOLDER = "First Name";
    public static final String LAST_NAME_TEXT_FIELD_PLACEHOLDER = "Last Name";
    public static final String POSTAL_CODE_TEXT_FIELD_PLACEHOLDER = "Zip/Postal Code";

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
        this.baseElementLocator = TITLE_LOCATOR;
    }

    public CheckoutOverviewPage validFillFormCheckoutStep1(String firstname, String lastname, String zipcode) {
        driver.findElement(FIRST_NAME_TEXT_FIELD).sendKeys(firstname);
        driver.findElement(LAST_NAME_TEXT_FIELD).sendKeys(lastname);
        driver.findElement(POSTAL_CODE_TEXT_FIELD).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    public CheckoutYourInformationPage invalidFillFormCheckoutStep1(String firstname, String lastname, String zipcode) {
        driver.findElement(FIRST_NAME_TEXT_FIELD).sendKeys(firstname);
        driver.findElement(LAST_NAME_TEXT_FIELD).sendKeys(lastname);
        driver.findElement(POSTAL_CODE_TEXT_FIELD).sendKeys(zipcode);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Override
    public CheckoutYourInformationPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public CheckoutYourInformationPage open() {
        return null;
    }
}
