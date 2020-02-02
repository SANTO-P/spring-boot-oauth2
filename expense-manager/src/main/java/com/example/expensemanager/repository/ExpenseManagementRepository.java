package com.example.expensemanager.repository;

import com.example.expensemanager.view.ExpenseView;
import com.example.expensemanager.vo.RoleType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseManagementRepository extends PagingAndSortingRepository<ExpenseView, String> {

    Optional<List<ExpenseView>> findByRoleTypeAndRoleId(RoleType roleType, String roleId);
    Optional<List<ExpenseView>> findByRoleType(RoleType roleType);
}
