package com.example.MerchantService.service;

import com.example.MerchantService.entity.Lend;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.repository.LendRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.util.DateGen;
import com.example.MerchantService.util.Response;
import com.example.MerchantService.util.SMSConfig;
import com.example.MerchantService.util.WhatsAppCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LendService {

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    LendRepository lendRepository;

    @Autowired
    WhatsAppCommunication whatsAppCommunication;

    @Autowired
    DateGen dateGen;

    @Autowired
    RestTemplate restTemplate;
    // create the loan transaction.
    public ResponseEntity<Response>  createTransaction(String merchantUsername,Lend lend){
        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        lend.setLoanRequestedBy(merchantUsername);
        lend.setLoanDate(dateGen.genDate());
        lend.setLoanGiven(false);

        Lend createdLen = lendRepository.save(lend);

        if(createdLen == null){
            response.setMessage("could not create the transaction");
            response.setSuccess(false);
            response.setResponseObject(null);
        }
        else{
            response.setResponseObject(createdLen);
            response.setSuccess(true);
            response.setMessage("Transaction created successfully");
        }
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    // fetch all the loans merchant has given.
    public ResponseEntity<Response> getAllLendsByMerchant(String merchantName){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        List<Lend> lends = lendRepository.getAllLendsByMerchant(merchantName);
        return getResponseResponseEntity(response, lends);
    }

    private ResponseEntity<Response> getResponseResponseEntity(Response response, List<Lend> lends) {
        ResponseEntity<Response> responseEntity;
        if(lends == null || lends.size() == 0){
            response.setMessage("Merchant has not given any loans");
        }
        else{
            response.setMessage("Lends fetched successfully");
        }
        response.setSuccess(true);
        response.setResponseObject(lends);
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    // fetch all the loans merchant has taken
    public ResponseEntity<Response> getAllBorrowsByMerchant(String merchantUsername){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        List<Lend> borrows = lendRepository.getAllBorrowsByMerchant(merchantUsername);
        return getResponseResponseEntity(response, borrows);
    }

    // mark a loan as approved.
    public ResponseEntity<Response> approveALoan(String merchantUsername,int lendId){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();


        Optional<Lend> lendOpt = lendRepository.findById(lendId);

        // improve code structure here.
        Lend lend = null;
        if(lendOpt.isPresent()){
            lend = lendOpt.get();
        }
        if(lendOpt.isPresent() == false){
            response.setMessage("The transaction does not exist");
            response.setSuccess(false);
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else if(!lend.getLoanRequestedFrom().equals(merchantUsername)){
            response.setMessage("The merchant is not authorized to lend.");
            response.setSuccess(false);
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            Merchant lender = merchantRepository.getPassword(lend.getLoanRequestedFrom());
            Merchant borrower = merchantRepository.getPassword(lend.getLoanRequestedBy());

            try{
                lendRepository.updateTheTransaction(lendId);
            }
            catch (Exception e){
                response.setMessage("Error occured while updating");
                response.setSuccess(false);
                response.setResponseObject(null);
                responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                return responseEntity;
            }

            SMSConfig smsConfig = new SMSConfig();
            String message = "Dear customer.\n Your loan request for Rs. "+lend.getLoanAmount()+" has been approved by "+ lender.getStoreName();
            String uri = "https://rapidapi.rmlconnect.net:9443/bulksms/bulksms?username="+ smsConfig.username +"&password="+smsConfig.password+"&type=1&dlr=1&destination=91"+borrower.getPhoneNumber()+"&source=RMLPRD&message="+message;
            String result = restTemplate.getForObject(uri, String.class);

            HashMap<String, Object> hMap = new HashMap<>();
            hMap.put("sms_message_sent","true");
            response.setSuccess(true);
            response.setResponseObject(hMap);
            response.setMessage("The loan has been approved successfully");
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> updateMerchantAsLender(String merchantUsername){
        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        /**
         * Great learning here, always try to use Integer/Double etc in java
         * saves a lot of headache
         */
        Merchant m = merchantRepository.getPassword(merchantUsername);
        if(m != null && m.isLender()){
            m.setPassword(null);
            response.setSuccess(true);
            response.setMessage("merchant is already marked as lender");
            response.setResponseObject(m);
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
            return responseEntity;
        }
        try{
            merchantRepository.updateMerchantAsLender(merchantUsername);
            m.setPassword(null);
            response.setSuccess(true);
            response.setMessage("updated merchant lending status");
            response.setResponseObject(m);
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            response.setSuccess(false);
            response.setMessage("could not update lender status");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return  responseEntity;
    }

}
