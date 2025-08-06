package org.example.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements Driver {
    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver();
    }
}
