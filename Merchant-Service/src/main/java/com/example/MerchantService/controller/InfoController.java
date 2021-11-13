package com.example.MerchantService.controller;

import com.example.MerchantService.service.InfoService;
import com.example.MerchantService.util.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Autowired
    InfoService infoService;

    @GetMapping("/info/getCustomerNum/{merchantUsername}")
    public ResponseEntity<Response> getCustomerNumbers(@PathVariable("merchantUsername") String merchantUsername){
        return infoService.getAllCustomerNumbers(merchantUsername);
    }

    @GetMapping("/info/getMerchantRank/{merchantUsername}")
    public ResponseEntity<Response> getMerchantRank(@PathVariable("merchantUsername") String merchantUsername){
        return infoService.getMerchantRank(merchantUsername);
    }

    @GetMapping("info/getMerchantMoneyData/{merchantUsername}")
    public ResponseEntity<Response> getMerchantMoneyData(@PathVariable("merchantUsername") String merchantUsername){
        return infoService.getMerchantsExpensesAndRevenue(merchantUsername);
    }

    @GetMapping("/info/getAllLenders/{merchantUsername}")
    public ResponseEntity<Response> getAllLenders(@PathVariable("merchantUsername") String merchantUsername) {
        return infoService.getAllLenders(merchantUsername);
    }
}
