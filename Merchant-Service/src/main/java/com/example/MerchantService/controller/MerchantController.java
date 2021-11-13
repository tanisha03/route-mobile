package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.MerchantKey;
import com.example.MerchantService.service.MerchantService;
import com.example.MerchantService.util.Response;
import com.example.MerchantService.util.UniqueStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantController {

    @Autowired
    UniqueStringGenerator uniqueStringGenerator;

    @Autowired
    MerchantService merchantService;

    @GetMapping("/")
    public String checkApi(){
        return "Hello World and Happy Diwali";
    }


    @PostMapping("/merchant/create")
    public ResponseEntity<Response> createMerchant(@RequestBody Merchant merchant){
        merchant.setMerchantKey(new MerchantKey(uniqueStringGenerator.getUniqueString(),merchant.getMerchantKey().getUsername()));
        ResponseEntity<Response> response = merchantService.registerMerchant(merchant);
        return response;
    }

    @PostMapping("/merchant/login")
    public ResponseEntity<Response> getMerchant(@RequestBody Merchant merchant){
        ResponseEntity<Response> response = merchantService.loginMerchant(merchant);
        return response;
    }

    @GetMapping("/merchant/exists/{username}")
    public ResponseEntity<Response> checkMerchantExistence(@PathVariable("username") String username){
        return merchantService.checkMerchantExists(username);
    }

    @GetMapping("/merchant/all")
    public ResponseEntity<Response> getAllMerchants(){
        return merchantService.getAllMerchants();
    }
}
