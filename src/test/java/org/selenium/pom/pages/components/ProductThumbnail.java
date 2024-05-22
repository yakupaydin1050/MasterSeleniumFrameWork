package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

/**
 * @author : yakup.aydin
 * @date : 05.05.2024
 * @project : MasterSeleniumFramework
 */
public class ProductThumbnail extends BasePage {

    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtnElement(String productName) {
        return By.xpath("//*[@aria-label='Add “"+productName+"” to your cart']");
    }

    public ProductThumbnail clickAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCartBtnElement(productName);
        driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart() {
        WebElement e = waitLong.until(ExpectedConditions.elementToBeClickable(viewCartLink));
        e.click();
        return new CartPage(driver);
    }
}
