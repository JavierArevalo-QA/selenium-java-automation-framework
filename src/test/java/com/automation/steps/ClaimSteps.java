package com.automation.steps;

import com.automation.pages.AssignClaimPage;
import com.automation.pages.ClaimPage;
import com.automation.pages.EmployeeClaimsPage;
import com.automation.pages.ExpensePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.automation.core.context.ScenarioContext;
import com.automation.core.context.ScenarioContextManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClaimSteps {

    private final ClaimPage claimPage =
            new ClaimPage();

    private final AssignClaimPage assignClaimPage =
            new AssignClaimPage();

    private final ExpensePage expensePage =
            new ExpensePage();

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");



    private final EmployeeClaimsPage employeeClaimsPage =
            new EmployeeClaimsPage();

    @Given("the user opens the Claim module")
    public void openClaimModule() {

        claimPage.openClaimModule();
    }


    @Given("the user opens Assign Claim")
    public void openAssignClaim() {

        claimPage.openAssignClaim();
    }


    @When("the user creates a Medical Reimbursement claim with Euro currency and a complete description")
    public void createMedicalClaim() {

        ScenarioContext context =
                ScenarioContextManager.getContext();

        String description =
                "Automation Claim "
                        + System.currentTimeMillis();

        context.setClaimDescription(description);

        assignClaimPage
                .selectFirstEmployeeSuggestion("test")
                .selectEvent("Medical Reimbursement")
                .selectCurrency("Euro")
                .enterDescription(
                        "Medical reimbursement automation test"
                )
                .assign();
    }


    @Then("the claim success message should be displayed")
    public void validateClaimSuccess() {

        assertTrue(
                assignClaimPage.isSuccessMessageDisplayed(),
                "Claim success message was not displayed."
        );
    }


    @When(
            "the user adds a Transportation expense dated 2 weeks ago with amount {double}"
    )
    public void addTransportationExpense(double amount) {

        BigDecimal expenseAmount = BigDecimal.valueOf(amount);

        ScenarioContextManager
                .getContext()
                .setTransportationAmount(expenseAmount);


        String date =
                LocalDate.now()
                        .minusWeeks(2)
                        .format(dateFormatter);

        expensePage
                .clickAddExpense()
                .selectExpenseType("Transport")
                .enterDate(date)
                .enterAmount(BigDecimal.valueOf(amount))
                .saveExpense();
    }


    @When(
            "the user adds a Planned Surgery expense dated 1 week ago with amount {double}"
    )
    public void addSurgeryExpense(double amount) {

        BigDecimal expenseAmount = BigDecimal.valueOf(amount);

        ScenarioContextManager
                .getContext()
                .setPlannedSurgeryAmount(expenseAmount);


        String date =
                LocalDate.now()
                        .minusWeeks(1)
                        .format(dateFormatter);

        expensePage
                .clickAddExpense()
                .selectExpenseType("Planned Surgery")
                .enterDate(date)
                .enterAmount(BigDecimal.valueOf(amount))
                .saveExpense();
    }


    @Then("the expense success message should be displayed")
    public void validateExpenseSuccess() {

        assertTrue(
                expensePage.isSuccessMessageDisplayed(),
                "Expense success message was not displayed."
        );
    }


    @Then("both expenses should be displayed")
    public void validateExpenses() {

        assertTrue(
                expensePage.isExpenseDisplayed("Transport")
        );

        assertTrue(
                expensePage.isExpenseDisplayed("Planned Surgery")
        );
    }

    @Then("the total amount should match the sum of both expenses")
    public void validateTotalAmount() {

        ScenarioContext context =
                ScenarioContextManager.getContext();

        BigDecimal expectedTotal =
                context.getTransportationAmount()
                        .add(context.getPlannedSurgeryAmount());

        context.setExpectedTotal(expectedTotal);

        BigDecimal actualTotal =
                expensePage.getTotalAmount();

        assertEquals(
                0,
                actualTotal.compareTo(expectedTotal),
                "Expected total: "
                        + expectedTotal
                        + " but displayed total was: "
                        + actualTotal
        );
    }

    @When("the user submits the claim")
    public void submitClaim() {

        String referenceId =
                claimPage.getReferenceId();

        ScenarioContextManager
                .getContext()
                .setClaimReferenceId(referenceId);

        System.out.println(
                "CLAIM REFERENCE ID = " + referenceId
        );

        claimPage.submitClaim();
    }

    @Then("the claim submission success message should be displayed")
    public void validateClaimSubmissionSuccess() {

        assertTrue(
                claimPage.isSuccessMessageDisplayed(),
                "Claim submission success message was not displayed."
        );
    }

    @Then("the submitted claim should be displayed in Employee Claims")
    public void validateClaimInEmployeeClaims() {

        ScenarioContext context =
                ScenarioContextManager.getContext();

        String referenceId =
                context.getClaimReferenceId();

        claimPage.openEmployeeClaims();

        assertTrue(
                employeeClaimsPage.isClaimDisplayed(referenceId),
                "Claim with reference ID "
                        + referenceId
                        + " was not found."
        );

        assertTrue(
                employeeClaimsPage.claimContains(
                        referenceId,
                        "Medical Reimbursement",
                        "Euro",
                        new BigDecimal("1000.00")
                ),
                "The submitted claim does not contain the expected values."
        );
    }
}