package com.automation.steps;

import com.automation.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnLoginPage() {
        loginPage = new LoginPage();
    }

    @Given("the user is logged into OrangeHRM")
    public void userIsLoggedIntoOrangeHRM() {

        loginPage = new LoginPage();

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();

        assertTrue(
                loginPage.isDashboardDisplayed(),
                "Login failed."
        );
    }

    @When("the user logs in with username {string} and password {string}")
    public void login(String username, String password) {

        loginPage
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    @Then("the Dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {

        assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard was not displayed after login."
        );
    }
}