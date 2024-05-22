package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;

    protected WebDriverWait waitLong;
    protected WebDriverWait waitShort;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void load(String endPoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays =  driver.findElements(overlay);
        System.out.println("Overlay size; " + overlays.size());
        if (overlays.size() > 0) {
            waitLong.until(
                    ExpectedConditions.invisibilityOfAllElements(overlays)
            );
            System.out.println("Overlay size are invisible");
        } else {
            System.out.println("OVERLAY NOT FOUND");
        }
    }


    public WebElement waitForElementVisible(By element) {
        return  waitLong.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


}


// this is coming from a Selenium Conference! not Udemy!
/*    @Test
    public void howToUserPageFactories() {
        WebDriver driver = new FirefoxDriver();
        PageFactory.initElements(
                field -> {
                    Button button = field.getAnnotation(Button.class);
                    return new DefaultElementLocator(driver, field);
                },
                new Object());
    }*/