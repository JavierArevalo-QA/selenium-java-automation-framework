package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClaimPage extends BasePage {

    private final By claimMenu =
            By.xpath("//span[normalize-space()='Claim']");

    private final By assignClaimTab =
            By.xpath("//*[normalize-space()='Assign Claim']");

    private final By employeeClaimsTab =
            By.xpath("//*[normalize-space()='Employee Claims']");

    private final By submitButton =
            By.xpath("//button[normalize-space()='Submit']");

    private final By successToast =
            By.xpath("//div[contains(@class,'oxd-toast')]");


    private final By referenceIdInput =
            By.xpath(
                    "//label[normalize-space()='Reference Id']" +
                            "/ancestor::div[contains(@class,'oxd-input-group')]" +
                            "//input"
            );


    public ClaimPage openClaimModule() {

        click(claimMenu);

        return this;
    }

    public ClaimPage openAssignClaim() {

        click(assignClaimTab);

        return this;
    }

    public ClaimPage openEmployeeClaims() {

        click(employeeClaimsTab);

        return this;
    }

    public String getReferenceId() {

        WebElement element = find(referenceIdInput);

        String referenceId = element.getAttribute("value");

        if (referenceId == null || referenceId.isBlank()) {
            throw new IllegalStateException(
                    "Reference Id could not be retrieved."
            );
        }

        return referenceId.trim();
    }

    public ClaimPage submitClaim() {

        scrollTo(submitButton);

        click(submitButton);

        waitForLoaderToDisappear();

        return this;
    }

    public boolean isSuccessMessageDisplayed() {

        return isDisplayed(successToast);
    }
}