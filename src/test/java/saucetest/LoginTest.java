package saucetest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.pages.LoginPage;
import static web.pages.LoginPage.*;


public class LoginTest extends BaseTest {


    @DataProvider(name = "Invalid login data")
    public Object[][] inputForLoginForm() {
        return new Object[][]{
                {INVALID_USERNAME, PASSWORD, INVALID_USERNAME_PASSWORD_TEXT, "Error text is not correct"},
                {USERNAME, INVALID_PASSWORD, INVALID_USERNAME_PASSWORD_TEXT, "Error text is not correct"},
                {"", PASSWORD, EMPTY_USERNAME_TEXT, "Error text is not correct"},
                {USERNAME, "", EMPTY_PASSWORD_TEXT, "Error text is not correct"},
        };
    }

    @DataProvider(name = "check placeholders")
    public Object[][] inputForPlaceholder() {
        return new Object[][]{
                {USERNAME_TEXT_FIELD, USERNAME_TEXT_FIELD_PLACEHOLDER, "Username placeholder is wrong"},
                {PASSWORD_TEXT_FIELD, PASSWORD_TEXT_FIELD_PLACEHOLDER, "Password placeholder is wrong"},
        };
    }

    public void openLoginPage() {
        Assert.assertNotNull(
        loginPage
                 .open()
                 .isPageLoaded(),
                "Login page is not loaded"
        );
    }

    @Test (priority = 1)
    public void validCredentialsLoginTest() {
        openLoginPage();
        Assert.assertNotNull(
        loginPage
                .login(USERNAME, PASSWORD)
                .isPageLoaded(),
        "Catalog page is not loaded"
        );
    }

    @Test (dataProvider = "Invalid login data", description = "check all invalid login case", priority = 2)
    public void invalidLoginTest(String username, String password, String errorMessage, String message){
        openLoginPage();
        Assert.assertEquals(
        loginPage
                .invalidLogin(username,password)
                .getElementText(ERROR_TEXT_MESSAGE),
                 errorMessage,
                 message
        );
    }


    @Test(dataProvider ="check placeholders", description = "check fields placeholder", priority = 3)
    public void placeholderTest(By fieldLocator, String textPlaceholder, String message) {
        openLoginPage();
        Assert.assertEquals(
        loginPage
                .getPlaceholder(fieldLocator),
                textPlaceholder,
                message
        );
    }

    @Test (priority = 3, groups = "Failed")
    public void loginPageIsUnavailableForLoggedUser(){
        validCredentialsLoginTest();
        Assert.assertNull(
        loginPage.
                open(),
       "Login page is available for logged user"
        );
    }
}
