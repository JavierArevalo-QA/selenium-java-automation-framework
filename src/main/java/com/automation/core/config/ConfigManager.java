package com.automation.core.config;

public final class ConfigManager {

    private ConfigManager() {
    }

    public static String getBrowser() {

        return System.getProperty(
                "browser",
                "chrome"
        );
    }

    public static String getBaseUrl() {

        return System.getProperty(
                "baseUrl",
                "https://opensource-demo.orangehrmlive.com"
        );
    }
}