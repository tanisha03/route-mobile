package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Expense;
import com.example.MerchantService.service.ExpenseService;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expense/{merchantUsername}")
    public ResponseEntity<Response> createExpense(@PathVariable
                                                        ("merchantUsername") String merchantUsername, @RequestBody Expense expense){
        System.out.println(expense);
        ResponseEntity<Response> responseEntity = expenseService.createExpense(merchantUsername,expense);
        return responseEntity;
    }

    @GetMapping("/expense/{merchantUsername}")
    public ResponseEntity<Response> getExpenses(@PathVariable
                                                      ("merchantUsername") String merchantUsername){
        ResponseEntity<Response> responseEntity = expenseService.getAllExpenses(merchantUsername);
        return responseEntity;
    }

    @GetMapping("/expense/{merchantUsername}/{expenseId}")
    public ResponseEntity<Response> getExpense(@PathVariable
                                                     ("merchantUsername") String merchantUsername, @PathVariable("expenseId") int expenseId) {
        ResponseEntity<Response> responseEntity = expenseService.getExpense(merchantUsername, expenseId);
        return responseEntity;
    }

    @DeleteMapping("/expense/{merchantUsername}/{expenseId}")
    public ResponseEntity<Response> deleteExpense(@PathVariable
                                                        ("merchantUsername") String merchantUsername, @PathVariable("expenseId") int expenseId) {
        ResponseEntity<Response> responseEntity = expenseService.deleteExpense(merchantUsername, expenseId);
        return responseEntity;
    }
}
