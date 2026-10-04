package com.automation.pages;

import org.openqa.selenium.By;

import java.math.BigDecimal;

public class ExpensePage extends BasePage {

    private final By addExpenseButton =
            By.xpath(
                    "//h6[normalize-space()='Expenses']" +
                            "/following::button[contains(.,'Add')][1]"
            );

    private final By expenseTypeDropdown =
            By.xpath(
                    "//label[normalize-space()='Expense Type']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//div[contains(@class,'oxd-select-text')]"
            );

    private final By dateInput =
            By.xpath(
                    "//label[normalize-space()='Date']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//input"
            );

    private final By amountInput =
            By.xpath(
                    "//label[contains(normalize-space(),'Amount')]" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//input"
            );

    private final By saveButton =
            By.xpath(
                    "//button[normalize-space()='Save']"
            );

    private final By successToast =
            By.xpath(
                    "//div[contains(@class,'oxd-toast')]"
            );

    private final By totalAmount =
            By.xpath(
                    "//*[starts-with(normalize-space(.),'Total Amount (Euro)')]"
            );

    private final By addExpenseModal =
            By.xpath(
                    "//div[contains(@class,'oxd-dialog-container')]"
            );


    private By dropdownOption(String option) {

        return By.xpath(
                "//div[@role='option']//*[normalize-space()='"
                        + option +
                        "']"
        );
    }

    public BigDecimal getTotalAmount() {

        String text = getText(totalAmount);

        String value = text
                .substring(text.lastIndexOf(":") + 1)
                .trim()
                .replace(",", "");

        return new BigDecimal(value);
    }


    private By expenseRow(String expenseType) {

        return By.xpath(
                "//*[normalize-space()='"
                        + expenseType +
                        "']"
        );
    }


    public ExpensePage clickAddExpense() {

        waitForLoaderToDisappear();

        scrollTo(addExpenseButton);

        click(addExpenseButton);

        return this;
    }


    public ExpensePage selectExpenseType(String type) {

        click(expenseTypeDropdown);

        click(dropdownOption(type));

        return this;
    }


    public ExpensePage enterDate(String date) {

        type(dateInput, date);

        return this;
    }


    public ExpensePage enterAmount(BigDecimal amount) {

        type(
                amountInput,
                amount.toPlainString()
        );

        return this;
    }


    public ExpensePage saveExpense() {

        click(saveButton);

        waitForLoaderToDisappear();

        waitForInvisible(addExpenseModal);

        return this;
    }


    public boolean isExpenseDisplayed(String expense) {

        return isDisplayed(
                expenseRow(expense)
        );
    }


    public boolean isSuccessMessageDisplayed() {

        return isDisplayed(successToast);
    }


    public String getTotalAmountText() {

        return getText(totalAmount);
    }




}