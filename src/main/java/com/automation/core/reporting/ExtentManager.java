package com.automation.core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

    private static final ExtentReports EXTENT = createExtentReports();

    private ExtentManager() {
    }

    private static ExtentReports createExtentReports() {

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter(
                        "target/reports/ExtentReport.html"
                );

        sparkReporter.config()
                .setReportName("OrangeHRM Automation Report");

        sparkReporter.config()
                .setDocumentTitle("QA Automation Results");

        ExtentReports extentReports =
                new ExtentReports();

        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo(
                "Framework",
                "Selenium + Java + Cucumber + POM"
        );

        extentReports.setSystemInfo(
                "Java",
                System.getProperty("java.version")
        );

        extentReports.setSystemInfo(
                "OS",
                System.getProperty("os.name")
        );

        return extentReports;
    }

    public static ExtentReports getExtent() {
        return EXTENT;
    }

    public static void flush() {
        EXTENT.flush();
    }
}