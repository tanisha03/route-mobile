package com.example.CustomerService.controller;

import com.example.CustomerService.entity.Subscriber;
import com.example.CustomerService.service.SubscriberService;
import com.example.CustomerService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriberController {
    @Autowired
    SubscriberService subscriberService;

    @PostMapping("/customer/subscribe")
    public ResponseEntity<Response> addSubscription(@RequestBody Subscriber subscriber){
        return subscriberService.addSubscription(subscriber);
    }

    @GetMapping("/customer/getOffers/{merchantUsername}")
    public ResponseEntity<Response> getOffersByMerchant(@PathVariable("merchantUsername") String merchantUsername){
        return subscriberService.getOffersByMerchant(merchantUsername);
    }

    @GetMapping("/customer/getMerchants/{custEmail}")
    public ResponseEntity<Response> getMerchants(@PathVariable("custEmail") String custEmail){
        return subscriberService.getMerchants(custEmail);
    }
}
