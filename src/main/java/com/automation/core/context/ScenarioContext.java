package com.automation.core.context;

import java.math.BigDecimal;

public class ScenarioContext {

    private String claimReferenceId;
    private String claimDescription;
    private String employeeName;
    private BigDecimal expectedTotal;

    private BigDecimal transportationAmount;
    private BigDecimal plannedSurgeryAmount;

    public BigDecimal getTransportationAmount() {
        return transportationAmount;
    }

    public void setTransportationAmount(BigDecimal transportationAmount) {
        this.transportationAmount = transportationAmount;
    }

    public BigDecimal getPlannedSurgeryAmount() {
        return plannedSurgeryAmount;
    }

    public void setPlannedSurgeryAmount(BigDecimal plannedSurgeryAmount) {
        this.plannedSurgeryAmount = plannedSurgeryAmount;
    }

    public String getClaimReferenceId() {
        return claimReferenceId;
    }

    public void setClaimReferenceId(String claimReferenceId) {
        this.claimReferenceId = claimReferenceId;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getExpectedTotal() {
        return expectedTotal;
    }

    public void setExpectedTotal(BigDecimal expectedTotal) {
        this.expectedTotal = expectedTotal;
    }
}