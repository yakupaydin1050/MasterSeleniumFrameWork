package org.selenium.pom.factory.abstractFactroy;

import org.selenium.constants.BrowserType;
import org.selenium.pom.factory.ChromeDriverManager;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.FirefoxDriverManager;

/**
 * @author : yakup.aydin
 * @date : 06.05.2024
 * @project : MasterSeleniumFramework
 */
public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(BrowserType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagerAbstract();
            }
            default -> throw new IllegalStateException("Unexpected value: " + driverType);
        }
    }
}
