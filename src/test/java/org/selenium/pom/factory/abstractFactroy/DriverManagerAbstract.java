package org.selenium.pom.factory.abstractFactroy;

import org.openqa.selenium.WebDriver;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public abstract class DriverManagerAbstract {

    protected WebDriver driver;

    protected abstract void startDriver();

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            startDriver();
        }
        return driver;
    }


}
