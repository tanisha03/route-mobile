package com.example.MerchantService.service;

import com.example.MerchantService.entity.Expense;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.Expense;
import com.example.MerchantService.repository.ExpenseRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.ExpenseRepository;
import com.example.MerchantService.util.DateGen;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    DateGen dateGen;

    @Autowired
    MerchantRepository merchantRepository;
    public ResponseEntity<Response> createExpense(String merchantUsername, Expense expense){


        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        expense.setCreatedAt(dateGen.genDate());

        Merchant m = merchantRepository.getPassword(merchantUsername);

        if(m == null || m.getMerchantKey() == null  || m.getMerchantKey().getMerchantId() == null){
            response.setSuccess(false);
            response.setMessage("Error occured while creating expense");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        expense.setMerchantId(m.getMerchantKey().getMerchantId());
        expense.setMerchantUsername(merchantUsername);

        try{
            Expense createdExpense = expenseRepository.save(expense);

            if(createdExpense == null){
                response.setSuccess(false);
                response.setMessage("No such expense");
                response.setResponseObject(createdExpense);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {
                response.setSuccess(true);
                response.setMessage("Expense created successfully");
                response.setResponseObject(createdExpense);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex){

            response.setSuccess(false);
            response.setMessage("Error occured while creating the expense");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getExpense(String merchantUsername,int expenseId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            Expense expense = expenseRepository.findExpenseByMerchantUsernameAndExpenseId(merchantUsername,expenseId);
            if(expense == null){
                response.setSuccess(false);
                response.setMessage("No such expense");
                response.setResponseObject(expense);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            response.setSuccess(true);
            response.setResponseObject(expense);
            response.setMessage("Successfully fetched the expense");
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setResponseObject(new String(ex.getMessage()));
            response.setMessage("Error occurred while fetching object");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    public  ResponseEntity<Response> getAllExpenses(String merchantUsername){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            List<Expense> expense = expenseRepository.findExpenseByMerchantUsername(merchantUsername);
            response.setSuccess(true);
            response.setResponseObject(expense);
            response.setMessage("Successfully fetched the expense");
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setResponseObject(new String(ex.getMessage()));
            response.setMessage("Error occured while fetching objects");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public  ResponseEntity<Response> deleteExpense(String merchantUsername,int expenseId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            int id = expenseRepository.deleteExpenseByMerchantUsernameAndExpenseId(merchantUsername,expenseId);
            if(id > 0){
                response.setSuccess(true);
                response.setMessage("Successfully deleted the expense");
                responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
            }
            else{
                response.setSuccess(false);
                response.setMessage("Error occured while deleting object");
                responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setResponseObject(new String(ex.getMessage()));
            response.setMessage("Error occured while deleting object");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }
}
