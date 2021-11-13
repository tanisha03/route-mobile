package com.example.MerchantService.service;

import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.repository.PurchaseRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.PurchaseRepository;
import com.example.MerchantService.util.DateGen;
import com.example.MerchantService.util.Response;
import com.example.MerchantService.util.SMSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    DateGen dateGen;

    @Autowired
    SMSConfig smsConfig;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<Response> createPurchase(String merchantUsername, Purchase purchase){


        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        purchase.setCreatedAt(dateGen.genDate());

        Merchant m = merchantRepository.getPassword(merchantUsername);

        if(m == null || m.getMerchantKey() == null  || m.getMerchantKey().getMerchantId() == null){
            response.setSuccess(false);
            response.setMessage("Error occured while creating purchase");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        purchase.setMerchantId(m.getMerchantKey().getMerchantId());
        purchase.setMerchantUsername(merchantUsername);

        try{
            String message = "Dear customer.\n Your purchase of Rs "+purchase.getPurchaseAmount() + " from "+m.getStoreName() + " is completed. Please use the " +
                    "upi Id below to pay: "+m.getUpiId();
            String uri = "https://rapidapi.rmlconnect.net:9443/bulksms/bulksms?username="+ smsConfig.username +"&password="+smsConfig.password+"&type=1&dlr=1&destination=91"+purchase.getPurchaseCustNum()+"&source=RMLPRD&message="+message;
            String result = restTemplate.getForObject(uri, String.class);
            purchase.setTextMsgStatus(result);
            Purchase createdPurchase = purchaseRepository.save(purchase);

            if(createdPurchase == null){
                response.setSuccess(false);
                response.setMessage("No such purchase");
                response.setResponseObject(createdPurchase);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {
                response.setSuccess(true);
                response.setMessage("Purchase created successfully");
                response.setResponseObject(createdPurchase);

                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex){

            response.setSuccess(false);
            response.setMessage("Error occured while creating the purchase");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getPurchase(String merchantUsername,int purchaseId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            Purchase purchase = purchaseRepository.findPurchaseByMerchantUsernameAndPurchaseId(merchantUsername,purchaseId);
            if(purchase == null){
                response.setSuccess(false);
                response.setMessage("No such purchase");
                response.setResponseObject(purchase);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            response.setSuccess(true);
            response.setResponseObject(purchase);
            response.setMessage("Successfully fetched the purchase");
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

    public  ResponseEntity<Response> getAllPurchases(String merchantUsername){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            List<Purchase> purchase = purchaseRepository.findPurchaseByMerchantUsername(merchantUsername);
            response.setSuccess(true);
            response.setResponseObject(purchase);
            response.setMessage("Successfully fetched the purchase");
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

    public  ResponseEntity<Response> deletePurchase(String merchantUsername,int purchaseId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            int id = purchaseRepository.deletePurchaseByMerchantUsernameAndPurchaseId(merchantUsername,purchaseId);
            if(id > 0){
                response.setSuccess(true);
                response.setMessage("Successfully deleted the purchase");
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
