package org.selenium.pom.factory.abstractFactroy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.selenium.pom.factory.DriverManager;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
