package com.automation.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput =
            By.name("username");

    private final By passwordInput =
            By.name("password");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    private final By dashboardHeader =
            By.xpath("//h6[text()='Dashboard']");



    public LoginPage enterUsername(String username) {

        type(usernameInput, username);

        return this;
    }

    public LoginPage enterPassword(String password) {

        type(passwordInput, password);

        return this;
    }

    public LoginPage clickLogin() {

        click(loginButton);

        return this;
    }

    public boolean isDashboardDisplayed() {

        return isDisplayed(dashboardHeader);
    }
}