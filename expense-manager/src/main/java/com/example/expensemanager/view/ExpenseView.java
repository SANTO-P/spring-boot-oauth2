package com.example.expensemanager.view;

import com.example.expensemanager.vo.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class ExpenseView {

    @Id
    private String invoiceNumber;
    private Vendor vendor;
    private BigDecimal amount;
    private Date invoiceDate;
    private ExpenseState expenseState;
    private Date statusUpdateTimeStamp;
    private ReasonCode reasonCode;
    private RoleType roleType;
    private String roleId;

    @SuppressWarnings("unused")
    public ExpenseView() {
    }

    public ExpenseView(String invoiceNumber, Vendor vendor, BigDecimal amount, Date invoiceDate, ExpenseState expenseState, Date statusUpdateTimeStamp, ReasonCode reasonCode, RoleType roleType, String roleId) {
        this.invoiceNumber = invoiceNumber;
        this.vendor = vendor;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.expenseState = expenseState;
        this.statusUpdateTimeStamp = statusUpdateTimeStamp;
        this.reasonCode = reasonCode;
        this.roleType = roleType;
        this.roleId = roleId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public ExpenseState getExpenseState() {
        return expenseState;
    }

    public Date getStatusUpdateTimeStamp() {
        return statusUpdateTimeStamp;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setExpenseState(ExpenseState expenseState) {
        this.expenseState = expenseState;
    }

    public void setStatusUpdateTimeStamp(Date statusUpdateTimeStamp) {
        this.statusUpdateTimeStamp = statusUpdateTimeStamp;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseView view = (ExpenseView) o;
        return Objects.equals(invoiceNumber, view.invoiceNumber) &&
                vendor == view.vendor &&
                Objects.equals(amount, view.amount) &&
                Objects.equals(invoiceDate, view.invoiceDate) &&
                expenseState == view.expenseState &&
                Objects.equals(statusUpdateTimeStamp, view.statusUpdateTimeStamp) &&
                reasonCode == view.reasonCode &&
                roleType == view.roleType &&
                Objects.equals(roleId, view.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, vendor, amount, invoiceDate, expenseState, statusUpdateTimeStamp, reasonCode, roleType, roleId);
    }
}
