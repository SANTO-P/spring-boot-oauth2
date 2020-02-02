package com.example.expensemanager.vo;

import java.util.Date;

public class ExpenseStatus {

    private ExpenseStatus expenseStatus;
    private Date timeStamp;
    private ReasonCode reasonCode;

    @SuppressWarnings("ununsed")
    public ExpenseStatus() {
    }

    public ExpenseStatus(ExpenseStatus expenseStatus, Date timeStamp, ReasonCode reasonCode) {
        this.expenseStatus = expenseStatus;
        this.timeStamp = timeStamp;
        this.reasonCode = reasonCode;
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }
}
