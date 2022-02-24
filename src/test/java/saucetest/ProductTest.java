package saucetest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static web.pages.LoginPage.PASSWORD;
import static web.pages.LoginPage.USERNAME;


public class ProductTest extends BaseTest {

    public static final String TEST_PRODUCT_TITLE = "Test.allTheThings() T-Shirt (Red)";
    public static final String TEST_PRODUCT_TITLE_2 = "Sauce Labs Bike Light";

    @BeforeMethod
    private void loginAndEmptyCart(){
        loginPage
                .open()
                .isPageLoaded()
                .login(USERNAME, PASSWORD)
                .isPageLoaded();
        menuElement.resetAppState();
        Assert.assertTrue(
        cartPage
                .open()
                .isPageLoaded()
                .isCartEmpty(),
                "Cart is not empty"
        );
    }

    @Test (priority = 1)
    public void addProductToCartTest() {
        Assert.assertNotNull(
        catalogPage
                .open()
                .isPageLoaded()
                .addProductToCart(TEST_PRODUCT_TITLE)
                .openCartPageByCartIcon()
                .isPageLoaded()
                .areProductAdded(List.of(TEST_PRODUCT_TITLE)),
                "Product was not added to cart"
        );
    }

    @Test (priority = 2)
    public void isCartCountDisplayedTest(){
        Assert.assertNotNull(
        catalogPage
                .open()
                .isPageLoaded()
                .addProductToCart(TEST_PRODUCT_TITLE)
                .isCountDisplayed(),
                "Count on the Cart icon is not shown"
        );
    }

    @Test (priority = 2)
    public void isCartCountCorrectTest(){
        Assert.assertEquals(
        catalogPage
                .open()
                .isPageLoaded()
                .addProductToCart(TEST_PRODUCT_TITLE)
                .addProductToCart(TEST_PRODUCT_TITLE_2)
                .getCount(),
                2,
                "Count on Cart icon is wrong"
        );
    }

    @Test (priority = 1)
    public void removeAddedProductTest(){
        addProductToCartTest();
        Assert.assertNull(
        cartPage
                .removeProduct(TEST_PRODUCT_TITLE)
                .areProductAdded(List.of(TEST_PRODUCT_TITLE)),
                "Product was not removed"
        );
    }

    @Test (priority = 1)
    public void openProductPageTest(){
        Assert.assertNotNull(
        catalogPage
                .open()
                .isPageLoaded()
                .openProductPage(TEST_PRODUCT_TITLE)
                .isPageLoaded(),
                "Product page does not open"
        );
    }

    @Test (priority = 2)
    public void productTitleOnProductPageTest(){
        openProductPageTest();
        Assert.assertEquals(productPage.getProductTitle(TEST_PRODUCT_TITLE),
                TEST_PRODUCT_TITLE,
                "Titles do not match on Product page and in Catalog or other productId opens"
        );
    }

    @Test (priority = 1)
    public void addProductToCartFromProductPageTest(){
        openProductPageTest();
        Assert.assertNotNull(
        productPage
                .addProductToCartFromProductPage(TEST_PRODUCT_TITLE)
                .navigateToCartByCartIcon()
                .isPageLoaded()
                .areProductAdded(List.of(TEST_PRODUCT_TITLE)),
                "Product is not added to cart"
        );
    }

    @Test (priority = 2)
    public void productDescOnProductPageTest(){
        catalogPage
                .open()
                .isPageLoaded();
        String expectedResult = catalogPage.productDesc(TEST_PRODUCT_TITLE);
        openProductPageTest();
        Assert.assertEquals(
                productPage.getProductDesc(TEST_PRODUCT_TITLE),
                expectedResult,
                "Descriptions do not match on Product page and in Catalog or other productId opens"
        );
    }

    @Test (priority = 2)
    public void productPriceOnProductPageTest(){
        catalogPage
                .open()
                .isPageLoaded();
        String expectedResult = catalogPage.productPrice(TEST_PRODUCT_TITLE);
        openProductPageTest();
        Assert.assertEquals(
                productPage.getProductPrice(TEST_PRODUCT_TITLE),
                expectedResult,
                "Prices do not match on Product page and in Catalog or other productId opens"
        );
    }

    @Test (priority = 2)
    public void backToCatalogFromProductPageTest(){
        openProductPageTest();
        Assert.assertNotNull(
        productPage
                .backToCatalogPage()
                .isPageLoaded(),
                "'Back to Catalog' button does not work"
        );
    }

    @Test (priority = 2)
    public void continueShoppingTest(){
        Assert.assertNotNull(
        cartPage
                .continueShopping()
                .isPageLoaded(),
                "Continue button does not redirect to Catalog"
        );
    }
}
