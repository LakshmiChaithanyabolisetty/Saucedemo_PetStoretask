package org.example.configuration;

import org.example.exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static WebDriver driver;
    private DriverFactory(){
    }
    public static WebDriver getDriver() {
        if(driver == null) {

            String browser = ConfigReader.getProperty("browser").toUpperCase();
            String baseurl = ConfigReader.getProperty("baseUrl");

            Driver browserDriver = switch (browser) {
                case "CHROME" -> new Chrome();
                case "FIREFOX" -> new Firefox();
                case "EDGE" -> new Edge();
                default -> throw new UnsupportedBrowserException();
            };

            driver = browserDriver.getDriver();
            driver.manage().window().maximize();
            driver.get(baseurl);
        }
        return driver;
    }
    public static void closeDriver() {
        driver.quit();

    }
}
