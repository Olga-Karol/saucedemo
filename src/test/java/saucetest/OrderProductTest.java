package saucetest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static saucetest.ProductTest.TEST_PRODUCT_TITLE;
import static web.pages.CheckoutYourInformationPage.*;
import static web.pages.LoginPage.*;


public class OrderProductTest extends BaseTest{

    @DataProvider(name = "Invalid user info")
    public Object[][] inputForLoginForm() {
        return new Object[][]{
                {"", LAST_NAME,  ZIP_CODE, EMPTY_FIRST_NAME_TEXT},
                {FIRST_NAME, "",  ZIP_CODE, EMPTY_LAST_NAME_TEXT},
                {FIRST_NAME, LAST_NAME, "", EMPTY_POSTAL_CODE_TEXT},

        };
    }

    @DataProvider(name = "check placeholders on user info form")
    public Object[][] inputForPlaceholder() {
        return new Object[][]{
                {FIRST_NAME_TEXT_FIELD, FIRST_NAME_TEXT_FIELD_PLACEHOLDER, "Firstname placeholder is wrong"},
                {LAST_NAME_TEXT_FIELD, LAST_NAME_TEXT_FIELD_PLACEHOLDER, "Lastname placeholder is wrong"},
                {POSTAL_CODE_TEXT_FIELD, POSTAL_CODE_TEXT_FIELD_PLACEHOLDER, "Postal code placeholder is wrong"},
        };
    }

    @BeforeMethod
    private void login(){
        Assert.assertNotNull(
        loginPage
                .open()
                .login(USERNAME, PASSWORD)
        .isPageLoaded(),
                "Catalog page is not loaded"
        );
    }

    @Test (priority = 1)
    public void checkoutYourInformationPageTest() {
        Assert.assertNotNull(
        catalogPage
                .addProductToCart(TEST_PRODUCT_TITLE)
                .openCartPageByCartIcon()
                .clickCheckoutButton()
                .isPageLoaded(),
                "Checkout: Your information page does not open"
        );
    }

    @Test (priority = 1)
    public void checkoutOverviewPageTest() {
        checkoutYourInformationPageTest();
        Assert.assertNotNull(
        checkoutYourInformationPage
                .validFillFormCheckoutStep1(FIRST_NAME, LAST_NAME, ZIP_CODE)
                .isPageLoaded(),
                "Checkout: Overview page does not open"
        );
    }

    @Test (priority = 1)
    public void areProductsShownOnOverviewPageTest() {
        checkoutYourInformationPageTest();
        Assert.assertNotNull(
                checkoutYourInformationPage
                        .validFillFormCheckoutStep1(FIRST_NAME, LAST_NAME, ZIP_CODE)
                        .isPageLoaded()
                        .areProductAdded(List.of(TEST_PRODUCT_TITLE)),
                "Ordered products are not shown"
        );
    }

    @Test (priority = 1)
    public void checkoutFinishPageTest() {
        checkoutOverviewPageTest();
        Assert.assertNotNull(
        checkoutOverviewPage
                .clickFinishButton()
                .isPageLoaded(),
                "Checkout: Complete! page does not open"
        );
    }

    @Test(dataProvider = "Invalid user info", description = "check all error message for input fields", priority = 2,
            dependsOnMethods = {"checkoutYourInformationPageTest"})
    public void fieldIsRequiredTest(String firstname, String lastname, String zipCode, String errorMessage){
        checkoutYourInformationPageTest();
        checkoutYourInformationPage.invalidFillFormCheckoutStep1(firstname, lastname, zipCode);
        Assert.assertEquals(
                checkoutYourInformationPage.getElementText(ERROR_TEXT_MESSAGE),
                errorMessage,
                "Error message is incorrect"
        );
    }

    @Test (dataProvider = "check placeholders on user info form", description = "check all placeholders on Your Info page", priority = 3,
            dependsOnMethods = {"checkoutYourInformationPageTest"})
    public void PlaceholderTest(By fieldLocator, String textPlaceholder, String message) {
        checkoutYourInformationPageTest();
        Assert.assertEquals(
                checkoutYourInformationPage.getPlaceholder(fieldLocator),
                textPlaceholder,
                message
        );
    }
}

