package org.example.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements Driver {
    @Override
    public WebDriver getDriver() {
        return new ChromeDriver();
    }
}
