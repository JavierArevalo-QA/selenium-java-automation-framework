package com.automation.hooks;

import com.automation.core.config.ConfigManager;
import com.automation.core.driver.BrowserType;
import com.automation.core.driver.DriverFactory;
import com.automation.core.driver.DriverManager;
import com.automation.core.reporting.ExtentManager;
import com.automation.core.reporting.ExtentTestManager;
import com.automation.core.reporting.ScreenshotUtils;
import com.automation.core.context.ScenarioContextManager;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.WebDriver;



public class Hooks {

    @Before
    public void setUp(Scenario scenario) {

        String browser =
                ConfigManager.getBrowser().toUpperCase();

        BrowserType browserType =
                BrowserType.valueOf(browser);

        System.out.println(
                "STARTING SCENARIO: "
                        + scenario.getName()
                        + " | THREAD: "
                        + Thread.currentThread().threadId()
                        + " | BROWSER: "
                        + browserType
        );


        WebDriver driver =
                DriverFactory.createDriver(browserType);

        DriverManager.setDriver(driver);

        DriverManager
                .getDriver()
                .get(ConfigManager.getBaseUrl());
        ExtentTestManager.startTest(
                scenario.getName()
        );

        ExtentTestManager
                .getTest()
                .assignCategory(
                        scenario.getSourceTagNames()
                                .toArray(new String[0])
                );

        ExtentTestManager
                .getTest()
                .info(
                        "Browser: " + browserType
                );
    }

    @After
    public void tearDown(Scenario scenario) {

        System.out.println(
                "FINISHED SCENARIO: "
                        + scenario.getName()
                        + " | THREAD: "
                        + Thread.currentThread().threadId()
        );
        try {

            if (scenario.isFailed()) {

                String screenshotPath =
                        ScreenshotUtils.captureScreenshot(
                                scenario.getName()
                        );

                ExtentTestManager
                        .getTest()
                        .fail(
                                "Scenario failed",
                                MediaEntityBuilder
                                        .createScreenCaptureFromPath(
                                                screenshotPath
                                        )
                                        .build()
                        );

            } else {

                ExtentTestManager
                        .getTest()
                        .pass(
                                "Scenario passed successfully"
                        );
            }

        } finally {

            DriverManager.quitDriver();

            ExtentTestManager.removeTest();

            ScenarioContextManager.removeContext();
        }
    }

    @AfterAll
    public static void afterAll() {

        ExtentManager.flush();
    }
}