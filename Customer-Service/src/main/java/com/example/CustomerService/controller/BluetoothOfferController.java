package com.example.CustomerService.controller;

import com.example.CustomerService.entity.BOffer;
import com.example.CustomerService.repository.BluetoothOfferRepository;
import com.example.CustomerService.util.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant/bluetooth/offer")
// creating it in a hurry, so not very proper code, no error handling etc.
public class BluetoothOfferController {

    @Autowired
    BluetoothOfferRepository bluetoothOfferRepository;

    @GetMapping("/")
    public ResponseEntity<Response> getAllOffers(){
        List<BOffer> offers = bluetoothOfferRepository.findAll();
        Response response = new Response();
        response.setResponseObject(offers);
        response.setSuccess(true);
        response.setMessage("fetched all offers");
        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Response> createOffer(@RequestBody BOffer bluetoothOffer){
        System.out.println(bluetoothOffer);
        bluetoothOfferRepository.save(bluetoothOffer);
        Response response = new Response();
        response.setResponseObject(null);
        response.setSuccess(true);
        response.setMessage("Offer creation success");
        ResponseEntity responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

}
