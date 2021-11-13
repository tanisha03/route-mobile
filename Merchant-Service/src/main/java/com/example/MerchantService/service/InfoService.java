package com.example.MerchantService.service;

import com.example.MerchantService.entity.Expense;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.repository.ExpenseRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.PurchaseRepository;

import com.example.MerchantService.util.PurchaseCategory;
import com.example.MerchantService.util.RankData;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    public ResponseEntity<Response> getAllCustomerNumbers(String merchantUsername){
        List<Purchase> purchases = purchaseRepository.findPurchaseByMerchantUsername(merchantUsername);
        List<String> customerMobile = purchases.stream().map(purchase -> purchase.getPurchaseCustNum()).distinct().collect(Collectors.toList());

        ResponseEntity<Response> responseEntity;
        Response response = new Response();
        if(purchases == null || purchases.size() == 0){
            //deliberately sending as false.
            response.setMessage("No purchases yet");
            response.setSuccess(false);
            response.setResponseObject(customerMobile);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        else{
            response.setMessage("Sending customer mobile numbers");
            response.setSuccess(true);
            response.setResponseObject(customerMobile);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getMerchantRank(String merchantUsername){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;

        List<RankData> rankdata = purchaseRepository.getMerchantsWithRank();
        HashMap<String,Object> resultData = new HashMap<>();
        int rank = -1;
        double avgSales = 0;
        for(int i=0;i<rankdata.size();i++){
            if(rankdata.get(i).getMUsername().equals(merchantUsername)){
                rank = i + 1;
                avgSales = rankdata.get(i).getAvgSales();
                break;
            }
        }
        if(rank == -1){
            response.setMessage("The merchant does not exist");
            response.setSuccess(false);
            response.setResponseObject(rankdata);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{

            resultData.put("merchantRank",rank);
            resultData.put("merchantAvgSales",avgSales);
            resultData.put("allMerchantsRank",rankdata);
            response.setMessage("Rank successfully fetched");
            response.setResponseObject(resultData);
            response.setSuccess(true);
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getMerchantsExpensesAndRevenue(String merchantUsername){
        Merchant merchant = merchantRepository.getPassword(merchantUsername);
        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        if(merchant == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("Merchant Not found");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            Double expense = expenseRepository.getSumExpenses(merchantUsername);
            Double revenue = purchaseRepository.getSumPurchases(merchantUsername);
            boolean eligibleForLending = merchant.isLender();
            boolean eligibleForBorrowing = false;
            // If the difference between revenue and lending is
            // greater than Rs 1Lakh, then we make the merchant
            // eligible for lending.

            if(revenue-expense >= 10000){
                eligibleForBorrowing = true;
            }
            if(!merchant.isLender() && (revenue-expense) >= 100000){
                eligibleForLending = true;
            }
            boolean isLender = merchant.isLender();

            HashMap<String,Object> infoData = new HashMap<>();
            infoData.put("expense",expense);
            infoData.put("revenue",revenue);
            infoData.put("isLender",isLender);
            infoData.put("eligibleForLending",eligibleForLending);
            infoData.put("eligibleForBorrowing",eligibleForBorrowing);

            response.setResponseObject(infoData);
            response.setSuccess(true);
            response.setMessage("Data fetched successfully");
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getAllLenders(String merchantUsername){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;

        List<Merchant> eligibleMerchants = merchantRepository.getEligibleMerchants(merchantUsername);
        if(eligibleMerchants == null){
            response.setMessage("could not find any merchants");
            response.setResponseObject(null);
            response.setSuccess(false);
        }
        else{
            for(Merchant m: eligibleMerchants){
                m.setPassword(null);
            }
            response.setResponseObject(eligibleMerchants);
            response.setSuccess(true);
            response.setMessage("lenders fetched successfully");
        }
        responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        return responseEntity;
    }

}
