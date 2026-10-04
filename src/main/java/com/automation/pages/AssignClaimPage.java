package com.automation.pages;

import org.openqa.selenium.By;

public class AssignClaimPage extends BasePage {

    private final By employeeNameInput =
            By.xpath(
                    "//label[normalize-space()='Employee Name']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//input"
            );


    private By firstEmployeeSuggestion =
            By.xpath("(//div[@role='listbox']//div[@role='option'])[2]");
    private final By eventDropdown =
            By.xpath(
                    "//label[normalize-space()='Event']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//div[contains(@class,'oxd-select-text')]"
            );

    private final By currencyDropdown =
            By.xpath(
                    "//label[normalize-space()='Currency']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//div[contains(@class,'oxd-select-text')]"
            );

    private final By remarks =
            By.xpath(
                    "//label[normalize-space()='Remarks']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//textarea"
            );

    private final By assignButton =
            By.xpath(
                    "//button[@type='submit']"
            );

    private final By successToast =
            By.xpath(
                    "//div[contains(@class,'oxd-toast')]"
            );


    private By dropdownOption(String option) {

        return By.xpath(
                "//div[@role='option']//*[normalize-space()='"
                        + option +
                        "']"
        );
    }


    public AssignClaimPage selectFirstEmployeeSuggestion(
            String searchText) {

        type(employeeNameInput, searchText);

        click(firstEmployeeSuggestion);

        return this;
    }


    public AssignClaimPage selectEvent(String event) {

        click(eventDropdown);

        click(dropdownOption(event));

        return this;
    }


    public AssignClaimPage selectCurrency(String currency) {

        click(currencyDropdown);

        click(dropdownOption(currency));

        return this;
    }


    public AssignClaimPage enterDescription(String description) {

        type(remarks, description);

        return this;
    }


    public AssignClaimPage assign() {

        click(assignButton);

        return this;
    }


    public boolean isSuccessMessageDisplayed() {

        return isDisplayed(successToast);
    }
}