package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Expense;
import com.example.MerchantService.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findExpenseByMerchantUsername(String merchantName);
    Expense findExpenseByMerchantUsernameAndExpenseId(String merchantUsername,int expenseId);

    @Transactional
    int deleteExpenseByMerchantUsernameAndExpenseId(String merchantUsername,int expenseId);

    @Query(value = "select sum(expamt) from expense where musername=:mUsername",nativeQuery = true)
    double getSumExpenses(String mUsername);
}
