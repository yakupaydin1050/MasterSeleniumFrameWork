package org.selenium.pom.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest extends BaseTest {

    String searchFor = "Blue";

    @Test
    public void searchWithPartialMatch() {
        StorePage storePage = new StorePage(getDriver()).
                load().
                search(searchFor);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("Blue"));
        Assert.assertTrue(storePage.getTitle().contains("Search results"));
    }
}
