package com.automation.core.reporting;

import com.automation.core.driver.DriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String captureScreenshot(String scenarioName) {

        TakesScreenshot screenshotDriver =
                (TakesScreenshot) DriverManager.getDriver();

        File source =
                screenshotDriver.getScreenshotAs(OutputType.FILE);

        String safeName =
                scenarioName.replaceAll(
                        "[^a-zA-Z0-9-_]",
                        "_"
                );

        String fileName =
                safeName
                        + "_"
                        + System.currentTimeMillis()
                        + ".png";

        Path destination =
                Path.of(
                        "target",
                        "reports",
                        "screenshots",
                        fileName
                );

        try {

            Files.createDirectories(
                    destination.getParent()
            );

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot.",
                    e
            );
        }

        return destination.toString();
    }
}