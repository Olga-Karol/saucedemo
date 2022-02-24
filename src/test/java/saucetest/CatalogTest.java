package saucetest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;
import static web.elements.MenuElement.*;
import static web.pages.LoginPage.PASSWORD;
import static web.pages.LoginPage.USERNAME;

public class CatalogTest extends BaseTest{

    public static final String SORT_NAME_A_TO_Z = ("Name (A to Z)");
    public static final String SORT_NAME_Z_TO_A = ("Name (Z to A)");
    public static final String SORT_PRICE_LOW_TO_HIGH = ("Price (low to high)");
    public static final String SORT_PRICE_HIGH_TO_LOW = ("Price (high to low)");

    @BeforeMethod
    private void login(){
        Assert.assertNotNull(
        loginPage
                .open()
                .isPageLoaded()
                .login(USERNAME, PASSWORD)
                .isPageLoaded(),
                "Catalog page does not open"
        );
    }

    @Test (priority = 1)
    public void isMenuBurgerAvailableTest(){
        Assert.assertNotNull(menuElement.isElementDisplayed(), "Burger menu icon is not shown");
    }

    @Test (priority = 1)
    public void isMenuOpenedTest(){
        menuElement.openMenu();
        Assert.assertNotNull(menuElement.isMenuOpened(), "Menu does not open");
    }

    @Test (priority = 1)
    public void logoutTest(){
        isMenuOpenedTest();
        menuElement.clickMenuLink(LOGOUT_LINK);
        Assert.assertNotNull(loginPage.isPageLoaded(), "User cannot logout");
    }

    @Test (priority = 2)
    public void navigateToCatalogTest(){
        cartPage.open();
        Assert.assertNotNull(
        menuElement
                .openMenu()
                .isMenuOpened()
                .clickAllItemsLink(),
                "ALL ITEMS link does not redirect to Catalog page"
        );
    }

    @Test (priority = 2)
    public void resetAppStateTest(){
        menuElement.resetAppState();
        Assert.assertNotNull(
        cartPage
                .open()
                .isCartEmpty(),
         "Cart was not cleared"
        );
    }

    @Test (priority = 2)
    public void aboutLinkTest(){
        isMenuOpenedTest();
        menuElement.clickMenuLink(ABOUT_LINK);
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://saucelabs.com/",
                "ABOUT link redirects to wrong page"
        );
    }

    @Test (priority = 1)
    public void isSortProductAvailableTest(){
        Assert.assertTrue(catalogPage.isSortAvailable(), "The product sort function is not available");
    }

    @Test (priority = 3, groups = "Flaky")
    public void checkDefaultProductSortOptionTest(){
        Assert.assertEquals(
                catalogPage.defaultSelectedSortOption(),
                SORT_NAME_A_TO_Z.toLowerCase(),
                "NAME (A to Z) is not default sort option"
        );
    }

    @Test (priority = 1)
    public void sortProductNameByAscTest(){
       List<String> beforeSortList = catalogPage.getProductTitleList();
       catalogPage.selectOptionProductSort(SORT_NAME_A_TO_Z);
       List<String> afterSortList = catalogPage.getProductTitleList();
       Assert.assertEquals(beforeSortList,afterSortList,"NAME (A to Z) option works incorrectly");

    }

    @Test (priority = 1)
    public void sortProductNameByDescTest(){
        List<String> beforeSortList = catalogPage.getProductTitleList();
        catalogPage.selectOptionProductSort(SORT_NAME_Z_TO_A);
        List<String> afterSortList = catalogPage.getProductTitleList();
        Collections.reverse(beforeSortList);
        Assert.assertEquals(beforeSortList,afterSortList,"NAME (Z to A) option works incorrectly");
    }

    @Test (priority = 1)
    public void sortProductPriceFromLowToHighTest(){
        List<Double> beforeSortList = catalogPage.getProductPriceList();
        catalogPage.selectOptionProductSort(SORT_PRICE_LOW_TO_HIGH);
        List<Double> afterSortList = catalogPage.getProductPriceList();
        Collections.sort(beforeSortList);
        Assert.assertEquals(beforeSortList,afterSortList,"Price (low to high) option works correctly");
    }

    @Test (priority = 1)
    public void sortProductPriceFromHighToLowTest(){
        catalogPage.selectOptionProductSort(SORT_PRICE_LOW_TO_HIGH);
        List<Double> beforeSortList = catalogPage.getProductPriceList();
        catalogPage.selectOptionProductSort(SORT_PRICE_HIGH_TO_LOW);
        List<Double> afterSortList = catalogPage.getProductPriceList();
        Collections.reverse(beforeSortList);
        Assert.assertEquals(beforeSortList,afterSortList,"Price (high to low) option works incorrectly");
    }
}
