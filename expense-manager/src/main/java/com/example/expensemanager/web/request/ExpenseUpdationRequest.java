package com.example.expensemanager.web.request;

import com.example.expensemanager.vo.ExpenseState;
import com.example.expensemanager.vo.ExpenseStatus;
import com.example.expensemanager.vo.ReasonCode;

import javax.validation.constraints.NotNull;

public class ExpenseUpdationRequest {

    @NotNull
    private ExpenseState expenseState;
    private ReasonCode reasonCode;

    @SuppressWarnings("unused")
    public ExpenseUpdationRequest() {
    }

    public ExpenseUpdationRequest(ExpenseState expenseState, ReasonCode reasonCode) {
        this.expenseState = expenseState;
        this.reasonCode = reasonCode;
    }

    public ExpenseState getExpenseState() {
        return expenseState;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }
}
