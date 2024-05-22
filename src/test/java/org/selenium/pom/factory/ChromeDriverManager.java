package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public class ChromeDriverManager implements DriverManager{


    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return  driver;
    }

}
