package com.example.expensemanager.service;

import com.example.expensemanager.repository.ExpenseManagementRepository;
import com.example.expensemanager.view.ExpenseView;
import com.example.expensemanager.vo.RoleType;
import com.example.expensemanager.web.request.ExpenseRequest;
import com.example.expensemanager.web.request.ExpenseUpdationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseManagementService {

    private ExpenseManagementRepository repository;
    Logger LOG =  LoggerFactory.getLogger(ExpenseManagementService.class);

    @Autowired
    public ExpenseManagementService(ExpenseManagementRepository repository) {
        this.repository = repository;
    }

    public void createExpense(ExpenseRequest request) {
        try {
            ExpenseView view = new ExpenseView(request.getInvoiceNumber(), request.getVendor(), request.getAmount(),
                                            request.getInvoiceDate(),null, null, null,
                                            request.getRoleInformation().getRoleType(),request.getRoleInformation().getRoleId());
            repository.save(view);
            LOG.info("Expense created successfully");
        }catch (Exception e){
            LOG.error("Expense creation failed with exception " + e.getStackTrace());
            throw e;
        }
    }

    public List<ExpenseView> getExpenses(RoleType roleType, String roleId) {
        return repository.findByRoleTypeAndRoleId(roleType, roleId).get();
    }

    public List<ExpenseView> getExpenses(RoleType roleType) {
        return repository.findByRoleType(roleType).get();
    }

    public void updateExpenseStatus(String invoiceNumber, ExpenseUpdationRequest request) throws Exception {
        Optional<ExpenseView> optionalView = repository.findById(invoiceNumber);
        if(!optionalView.isPresent()){
            LOG.error("expense does not exist in the system for invoice number "+invoiceNumber);
            throw new Exception();
        }

        ExpenseView view = optionalView.get();
        view.setExpenseState(request.getExpenseState());
        view.setStatusUpdateTimeStamp(new Date());
        view.setReasonCode(request.getReasonCode());
        repository.save(view);
        LOG.info("Expense updated successfully");
    }
}
