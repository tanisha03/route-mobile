package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.service.PurchaseService;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchase/{merchantUsername}")
    public ResponseEntity<Response> createPurchase(@PathVariable
                                                        ("merchantUsername") String merchantUsername, @RequestBody Purchase purchase){
        System.out.println(purchase);
        ResponseEntity<Response> responseEntity = purchaseService.createPurchase(merchantUsername,purchase);
        return responseEntity;
    }

    @GetMapping("/purchase/{merchantUsername}")
    public ResponseEntity<Response> getPurchases(@PathVariable
                                                      ("merchantUsername") String merchantUsername){
        ResponseEntity<Response> responseEntity = purchaseService.getAllPurchases(merchantUsername);
        return responseEntity;
    }

    @GetMapping("/purchase/{merchantUsername}/{purchaseId}")
    public ResponseEntity<Response> getPurchase(@PathVariable
                                                     ("merchantUsername") String merchantUsername, @PathVariable("purchaseId") int purchaseId) {
        ResponseEntity<Response> responseEntity = purchaseService.getPurchase(merchantUsername, purchaseId);
        return responseEntity;
    }

    @DeleteMapping("/purchase/{merchantUsername}/{purchaseId}")
    public ResponseEntity<Response> deletePurchase(@PathVariable
                                                        ("merchantUsername") String merchantUsername, @PathVariable("purchaseId") int purchaseId) {
        ResponseEntity<Response> responseEntity = purchaseService.deletePurchase(merchantUsername, purchaseId);
        return responseEntity;
    }

}
