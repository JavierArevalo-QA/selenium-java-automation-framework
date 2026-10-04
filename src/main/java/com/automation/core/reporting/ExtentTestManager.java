package com.automation.core.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> TEST =
            new ThreadLocal<>();

    private ExtentTestManager() {
    }

    public static void startTest(String testName) {

        ExtentTest extentTest =
                ExtentManager
                        .getExtent()
                        .createTest(testName);

        TEST.set(extentTest);
    }

    public static ExtentTest getTest() {

        ExtentTest test = TEST.get();

        if (test == null) {
            throw new IllegalStateException(
                    "ExtentTest has not been initialized for this thread."
            );
        }

        return test;
    }

    public static void removeTest() {
        TEST.remove();
    }
}