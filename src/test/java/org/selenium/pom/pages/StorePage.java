package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private ProductThumbnail productThumbnail;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    private StorePage enterTextInSearchFld(String  txt) {
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(searchFld));
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public Boolean isLoaded() {
        return waitLong.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn() {
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    private By getAddToCartBtnElement(String productName) {
        return By.xpath("//*[@aria-label='Add “"+productName+"” to your cart']");
    }


}
