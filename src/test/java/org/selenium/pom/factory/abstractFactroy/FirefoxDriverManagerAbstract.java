package org.selenium.pom.factory.abstractFactroy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.factory.DriverManager;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public class FirefoxDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
