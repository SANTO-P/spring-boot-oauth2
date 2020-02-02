package com.example.expensemanager.web;

import com.example.expensemanager.service.ExpenseManagementService;
import com.example.expensemanager.view.ExpenseView;
import com.example.expensemanager.vo.RoleType;
import com.example.expensemanager.web.request.ExpenseRequest;
import com.example.expensemanager.web.request.ExpenseUpdationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseManagementController {

    private Logger LOG =  LoggerFactory.getLogger(ExpenseManagementController.class);
    private ExpenseManagementService expenseManagementService;

    @Autowired
    public ExpenseManagementController(ExpenseManagementService expenseManagementService) {
        this.expenseManagementService = expenseManagementService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('create_expense')")
    public ResponseEntity createExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
        try {
            expenseManagementService.createExpense(expenseRequest);
            return new ResponseEntity(CREATED);
        }catch (Exception e){
            LOG.error("Expense creation request failed with exception "+ e.getStackTrace());
            return new ResponseEntity(CONFLICT);
        }
    }

    @GetMapping("/{roleId}")
    @PreAuthorize("hasRole('ROLE_bookkeper')")
    public ResponseEntity<List<ExpenseView>> getExpenses(@PathVariable String roleId){
         List<ExpenseView> expenseViews = expenseManagementService.getExpenses(RoleType.BOOKKEPEER, roleId);
        ResponseEntity responseEntity = new ResponseEntity(expenseViews, OK);
        return responseEntity;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<List<ExpenseView>> getExpenses(){
        List<ExpenseView> expenseViews = expenseManagementService.getExpenses(RoleType.ADMIN);
        ResponseEntity responseEntity = new ResponseEntity(expenseViews, OK);
        return responseEntity;
    }

    @PutMapping("/{invoiceNumber}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity updateExpenseStatus(@PathVariable String invoiceNumber,
                                              @Valid @RequestBody ExpenseUpdationRequest expenseUpdationRequest){
        try{
            expenseManagementService.updateExpenseStatus(invoiceNumber, expenseUpdationRequest);
            return new ResponseEntity(OK);
        }catch (Exception e){
            LOG.error("Expense status updation failed with exception "+ e.getStackTrace());
            return new ResponseEntity(NOT_MODIFIED);
        }
    }
}
