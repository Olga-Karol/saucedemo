package saucetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import web.elements.CartElement;
import web.elements.MenuElement;
import web.pages.*;
import java.time.Duration;


public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutYourInformationPage checkoutYourInformationPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutCompletePage checkoutCompletePage;
    protected ProductPage productPage;
    protected MenuElement menuElement;
    protected CartElement cartElement;


    @BeforeClass
    public void setup(ITestContext iTestContext) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        setContextAttribute(iTestContext, "driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        productPage = new ProductPage(driver);
        menuElement = new MenuElement(driver);
        cartElement = new CartElement(driver);
    }

    private void setContextAttribute(ITestContext iTestContext, String attributeKey, Object attributeValue){
        iTestContext.setAttribute(attributeKey, attributeValue);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }
}
