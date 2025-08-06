package org.example.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Edge implements Driver {
    @Override
    public WebDriver getDriver() {
        return new EdgeDriver();
    }
}
