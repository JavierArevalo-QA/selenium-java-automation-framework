package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class EmployeeClaimsPage extends BasePage {

    private By claimRow(String referenceId) {

        return By.xpath(
                "//div[@role='row']" +
                        "[.//div[normalize-space()='" + referenceId + "']]"
        );
    }

    public boolean isClaimDisplayed(String referenceId) {

        return isDisplayed(
                claimRow(referenceId)
        );
    }

    public String getClaimRowText(String referenceId) {

        WebElement row = find(
                claimRow(referenceId)
        );

        return row.getText();
    }

    public boolean claimContains(
            String referenceId,
            String expectedEvent,
            String expectedCurrency,
            BigDecimal expectedAmount
    ) {

        String rowText =
                getClaimRowText(referenceId);

        String formattedAmount =
                String.format(
                        "%,.2f",
                        expectedAmount
                );

        return rowText.contains(expectedEvent)
                && rowText.contains(expectedCurrency)
                && rowText.contains(formattedAmount);
    }
}