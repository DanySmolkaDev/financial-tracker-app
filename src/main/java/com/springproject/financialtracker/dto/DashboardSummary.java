package com.springproject.financialtracker.dto;

import java.math.BigDecimal;

public class DashboardSummary {
    private final BigDecimal monthlyCashFlow;

    public DashboardSummary(BigDecimal monthlyCashFlow) {
        this.monthlyCashFlow = monthlyCashFlow;
    }

    public BigDecimal getMonthlyCashFlow() {return monthlyCashFlow;}
}
