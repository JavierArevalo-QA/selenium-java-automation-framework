@claims @regression
Feature: Claim management

  Background:
    Given the user is logged into OrangeHRM

  @critical @e2e
  Scenario: Submit a Medical Reimbursement claim with two expenses
    Given the user opens the Claim module
    And the user opens Assign Claim
    When the user creates a Medical Reimbursement claim with Euro currency and a complete description
    Then the claim success message should be displayed

    When the user adds a Transportation expense dated 2 weeks ago with amount 120.50
    Then the expense success message should be displayed

    When the user adds a Planned Surgery expense dated 1 week ago with amount 879.50
    Then the expense success message should be displayed

    And both expenses should be displayed
    And the total amount should match the sum of both expenses

    When the user submits the claim
    Then the claim submission success message should be displayed
    And the submitted claim should be displayed in Employee Claims
