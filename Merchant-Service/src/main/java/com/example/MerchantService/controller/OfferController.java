package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Offer;
import com.example.MerchantService.service.OfferService;
import com.example.MerchantService.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping("/offer/{merchantUsername}")
    public ResponseEntity<Response> createOffer(@PathVariable
            ("merchantUsername") String merchantUsername, @RequestBody Offer offer){
        System.out.println(offer);
        ResponseEntity<Response> responseEntity = offerService.createOffer(merchantUsername,offer);
        return responseEntity;
    }

    @GetMapping("/offer/{merchantUsername}")
    public ResponseEntity<Response> getOffers(@PathVariable
            ("merchantUsername") String merchantUsername){
        ResponseEntity<Response> responseEntity = offerService.getAllOffers(merchantUsername);
        return responseEntity;
    }

    @GetMapping("/offer/{merchantUsername}/{offerId}")
    public ResponseEntity<Response> getOffer(@PathVariable
            ("merchantUsername") String merchantUsername, @PathVariable("offerId") int offerId) {
        ResponseEntity<Response> responseEntity = offerService.getOffer(merchantUsername, offerId);
        return responseEntity;
    }

    @DeleteMapping("/offer/{merchantUsername}/{offerId}")
    public ResponseEntity<Response> deleteOffer(@PathVariable
            ("merchantUsername") String merchantUsername, @PathVariable("offerId") int offerId) {
        ResponseEntity<Response> responseEntity = offerService.deleteOffer(merchantUsername, offerId);
        return responseEntity;
    }
}
