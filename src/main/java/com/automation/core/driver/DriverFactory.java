package com.automation.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent instantiation
    }

    public static WebDriver createDriver(BrowserType browserType) {

        return switch (browserType) {

            case CHROME -> createChromeDriver();

            case EDGE -> createEdgeDriver();
        };
    }

    private static WebDriver createChromeDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver() {

        EdgeOptions options = new EdgeOptions();

        options.addArguments("--start-maximized");

        return new EdgeDriver(options);
    }
}