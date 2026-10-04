package com.automation.pages;

import com.automation.core.driver.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final By formLoader = By.cssSelector(".oxd-form-loader");

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement find(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    protected List<WebElement> findAll(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }

    protected void click(By locator) {

        waitForLoaderToDisappear();

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        element.click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);

        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    protected void scrollTo(By locator) {

        WebElement element = find(locator);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element
                );
    }

    protected void waitForLoaderToDisappear() {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(formLoader)
        );
    }

    protected void waitForInvisible(By locator) {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    protected void waitForToastToDisappear(By toastLocator) {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(toastLocator)
        );
    }
}