package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import web.base.BasePage;
import web.elements.CartElement;
import web.elements.MenuElement;

import java.util.ArrayList;
import java.util.List;


public class CatalogPage extends BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/inventory.html";
    private static final By TITLE_LOCATOR = By.xpath("//span[@class='title' and text()='Products']");
    private static final String PRODUCT_XPATH_PATTERN_ADD_TO_CART =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//button";
    private static final String PRODUCT_XPATH_PATTERN_LINK =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item_label']//a";
    private static final String PRODUCT_XPATH_PATTERN_DESC =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_desc']";
    private static final String PRODUCT_XPATH_PATTERN_PRICE =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']";
    private static final By DROPDOWN_FILTER = By.className("product_sort_container");
    private static final By FILTER_ACTIVE_OPTION = By.className("active_option");
    private static final By PRODUCT_NAME_IN_CATALOG = By.className("inventory_item_name");
    private static final By PRODUCT_PRICE_IN_CATALOG = By.className("inventory_item_price");
    private MenuElement menuElement;
    private CartElement cartElement;


    public CatalogPage(WebDriver driver) {
        super(driver);
        this.baseUrl = BASE_URL;
        this.baseElementLocator = TITLE_LOCATOR;
        this.menuElement = new MenuElement(driver);
        this.cartElement = new CartElement(driver);
    }

    public CatalogPage addProductToCart(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN_ADD_TO_CART, partialProductTitle))).click();
        return this;
    }

    public ProductPage openProductPage(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN_LINK, partialProductTitle))).click();
        return new ProductPage(driver);
    }

    public String productDesc(String partialProductTitle) {
        return getElementText(By.xpath(String.format(PRODUCT_XPATH_PATTERN_DESC, partialProductTitle)));
    }

    public String productPrice(String partialProductTitle) {
        String productPriceInCatalog = getElementText(By.xpath(String.format(PRODUCT_XPATH_PATTERN_PRICE, partialProductTitle)));
        return productPriceInCatalog;
    }

    public boolean isSortAvailable() {
        return driver.findElement(DROPDOWN_FILTER).isDisplayed();
    }

    public String defaultSelectedSortOption() {
        String defaultSelectedSortOption = driver.findElement(FILTER_ACTIVE_OPTION).getText().toLowerCase();
        return defaultSelectedSortOption;
    }

    public List<String> getProductTitleList() {
        List<WebElement> products = driver.findElements(PRODUCT_NAME_IN_CATALOG);
        List<String> productsList = new ArrayList<>();
        for (WebElement title : products) {
            productsList.add(title.getText());
        }
        return productsList;
    }

    public CartPage openCartPageByCartIcon() {
        cartElement.clickCartIcon();
        return new CartPage(driver);
    }

    public By isCountDisplayed(){
        return cartElement.isCountDisplayed();
    }

    public int getCount(){
        return cartElement.getCountValue();
    }

    public List<Double> getProductPriceList() {
        List<WebElement> prices = driver.findElements(PRODUCT_PRICE_IN_CATALOG);
        List<Double> priceList = new ArrayList<>();
        for (WebElement price : prices) {
            priceList.add(Double.valueOf(price.getText().replace("$", "")));
        }
        return priceList;
    }

    public void selectOptionProductSort(String option){
        Select dropDown = new Select(driver.findElement(DROPDOWN_FILTER));
        dropDown.selectByVisibleText(option);
    }

    @Override
    public CatalogPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public CatalogPage open() {
        driver.get(baseUrl);
        return this;
    }


}
