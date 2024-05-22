package org.selenium.pom.factory;

import org.selenium.constants.BrowserType;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public class DriverManagerFactory {

    public static DriverManager getManager(BrowserType browserType) {
        switch (browserType) {
            case CHROME -> {
                return new ChromeDriverManager();
            }
            case FIREFOX -> {
                return new FirefoxDriverManager();
            }
            default -> throw new IllegalStateException("Invalid browser type: " + browserType);
        }
    }
}
