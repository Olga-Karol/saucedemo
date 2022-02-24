package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BaseElement;
import web.pages.CatalogPage;

public class MenuElement extends BaseElement {

    public static final By MENU_BURGER_BUTTON = By.id("react-burger-menu-btn");
    private static final By MENU_BURGER_CLOSE_ICON = By.id("react-burger-cross-btn");
    private static final String MENU_BURGER_LINK_PATTERN = "//a[contains(text(),'%s')]";
    public static final String ALL_ITEMS_LINK = "All Items";
    public static final String ABOUT_LINK = "About";
    public static final String LOGOUT_LINK = "Logout";
    public static final String RESET_APP_STATE_LINK = "Reset App State";


    public MenuElement(WebDriver driver) {
        super(driver);
        this.baseElementLocator = MENU_BURGER_BUTTON;
    }

    @Override
    public MenuElement isElementDisplayed() {
        try {
            explicitlyWaitElement.until(ExpectedConditions.visibilityOfElementLocated(MENU_BURGER_BUTTON));
        } catch (TimeoutException ex) {
            return null;
        }
        return this;
    }

    public MenuElement isMenuOpened(){
        try {
            explicitlyWaitElement.until(ExpectedConditions.visibilityOfElementLocated(MENU_BURGER_CLOSE_ICON));
        } catch (TimeoutException ex) {
            return null;
        }
        return this;
    }


    public MenuElement openMenu(){
        driver.findElement(MENU_BURGER_BUTTON).click();
        return this;
    }

    public void clickMenuLink(String partialNameLink){
        driver.findElement(By.xpath(String.format(MENU_BURGER_LINK_PATTERN, partialNameLink))).click();
    }

    public CatalogPage resetAppState(){
        openMenu();
        isMenuOpened();
        clickMenuLink(RESET_APP_STATE_LINK);
        return new CatalogPage(driver);
    }

    public CatalogPage clickAllItemsLink(){
        clickMenuLink(ALL_ITEMS_LINK);
        return new CatalogPage(driver);
    }
}
