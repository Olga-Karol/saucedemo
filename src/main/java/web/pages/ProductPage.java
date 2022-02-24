package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import web.base.BasePage;
import web.elements.CartElement;

public class ProductPage extends BasePage {


    private static final By BACK_TO_PRODUCTS_BUTTON = By.id("back-to-products");
    private static final String PRODUCT_XPATH_PATTERN_ADD_TO_CART =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_details']//button";
    private static final String PRODUCT_XPATH_PATTERN_TITLE = "//div[contains(text(),'%s')]";
    private static final String PRODUCT_XPATH_PATTERN_DESC =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_details_desc_container']//div[@class='inventory_details_desc large_size']";
    private static final String PRODUCT_XPATH_PATTERN_PRICE =
            "//div[contains(text(),'%s')]/ancestor::div[@class='inventory_details_desc_container']//div[@class='inventory_details_price']";
    private CartElement cartElement;


    public ProductPage(WebDriver driver) {
        super(driver);
        this.baseElementLocator = BACK_TO_PRODUCTS_BUTTON;
        this.cartElement = new CartElement(driver);
    }

    public String getProductTitle(String partialProductTitle) {
        return getElementText((By.xpath(String.format(PRODUCT_XPATH_PATTERN_TITLE, partialProductTitle))));
    }

    public String getProductDesc(String partialProductTitle) {
        return getElementText(By.xpath(String.format(PRODUCT_XPATH_PATTERN_DESC, partialProductTitle)));
    }

    public String getProductPrice(String partialProductTitle) {
        return getElementText(By.xpath(String.format(PRODUCT_XPATH_PATTERN_PRICE, partialProductTitle)));
    }

    public ProductPage addProductToCartFromProductPage(String partialProductTitle) {
        driver.findElement(By.xpath(String.format(PRODUCT_XPATH_PATTERN_ADD_TO_CART, partialProductTitle))).click();
        return this;
    }

    public CatalogPage backToCatalogPage() {
        driver.findElement(BACK_TO_PRODUCTS_BUTTON).click();
        return new CatalogPage(driver);
    }

    public CartPage navigateToCartByCartIcon(){
       cartElement.clickCartIcon();
       return new CartPage(driver);
    }

    @Override
    public ProductPage isPageLoaded() {
        try {
            explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(baseElementLocator));
        } catch (TimeoutException ex) {
            System.out.println("Element was not found.");
            return null;
        }
        return this;
    }

    @Override
    public ProductPage open() {
        return null;
    }
}
