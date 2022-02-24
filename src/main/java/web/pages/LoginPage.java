package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;


public class LoginPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final By USERNAME_TEXT_FIELD = By.id("user-name");
    public static final By PASSWORD_TEXT_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    public static final String USERNAME_TEXT_FIELD_PLACEHOLDER = "Username";
    public static final String PASSWORD_TEXT_FIELD_PLACEHOLDER = "Password";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "invalid_sauce";
    public static final String INVALID_USERNAME_PASSWORD_TEXT =
            "Epic sadface: Username and password do not match any user in this service";
    public static final String EMPTY_USERNAME_TEXT = "Epic sadface: Username is required";
    public static final String EMPTY_PASSWORD_TEXT = "Epic sadface: Password is required";
    public static final By ERROR_TEXT_MESSAGE = By.tagName("h3");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = LOGIN_BUTTON;
    }

    public CatalogPage login(String validUsername, String validPassword) {
        driver.findElement(USERNAME_TEXT_FIELD).sendKeys(validUsername);
        driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(validPassword);
        driver.findElement(LOGIN_BUTTON).click();
        return new CatalogPage(driver);
    }

    public LoginPage invalidLogin (String username, String password) {
        driver.findElement(USERNAME_TEXT_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_TEXT_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Override
    public LoginPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public LoginPage open() {
        driver.get(baseUrl);
        return this;
    }
}
