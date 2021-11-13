package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.entity.Lend;
import com.example.MerchantService.service.LendService;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LendController {

    @Autowired
    LendService lendService;


    @PostMapping("/transaction/{merchantUsername}")
    public ResponseEntity<Response> createTransaction(@PathVariable("merchantUsername") String merchantUsername, @RequestBody Lend lend){
        return  lendService.createTransaction(merchantUsername,lend);
    }

    @GetMapping("/transaction/getLend/{merchantUsername}")
    public ResponseEntity<Response> getAllLendsByMerchant(@PathVariable("merchantUsername") String merchantUsername){
        return  lendService.getAllLendsByMerchant(merchantUsername);
    }

    @GetMapping("/transaction/getBorrows/{merchantUsername}")
    public ResponseEntity<Response> getAllBorrowsByMerchant(@PathVariable("merchantUsername") String merchantUsername){
        return lendService.getAllBorrowsByMerchant(merchantUsername);
    }


    @PostMapping("/transaction/approve/{merchantUsername}/{transactionId}")
    public ResponseEntity<Response> approveTransaction(@PathVariable("merchantUsername") String merchantUsername, @PathVariable("transactionId") int id){
        return lendService.approveALoan(merchantUsername,id);
    }

    @PostMapping("/transaction/markAsLender/{merchantUsername}")
    public ResponseEntity<Response> markAsLender(@PathVariable("merchantUsername") String merchantUsername){
        return lendService.updateMerchantAsLender(merchantUsername);
    }
}
