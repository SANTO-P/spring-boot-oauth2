package com.example.expensemanager.web.request;

import com.example.expensemanager.vo.RoleInformation;
import com.example.expensemanager.vo.Vendor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class ExpenseRequest {

    @NotNull
    private String invoiceNumber;
    @NotNull
    private Vendor vendor;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Date invoiceDate;
    @NotNull
    private RoleInformation roleInformation;

    @SuppressWarnings("unused")
    public ExpenseRequest() {
    }

    public ExpenseRequest(@NotNull String invoiceNumber, @NotNull Vendor vendor, @NotNull BigDecimal amount, @NotNull Date invoiceDate, @NotNull RoleInformation roleInformation) {
        this.invoiceNumber = invoiceNumber;
        this.vendor = vendor;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.roleInformation = roleInformation;
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

    public RoleInformation getRoleInformation() {
        return roleInformation;
    }
}
