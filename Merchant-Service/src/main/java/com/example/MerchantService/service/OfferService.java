package com.example.MerchantService.service;

import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.MerchantKey;
import com.example.MerchantService.entity.Offer;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.OfferRepository;
import com.example.MerchantService.util.DateGen;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: Remove code duplication, can be done by extracting the code inside catch
 * to a separate function.
 */
@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    DateGen dateGen;

    @Autowired
    MerchantRepository merchantRepository;
    public ResponseEntity<Response> createOffer(String merchantUsername, Offer offer){


        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        offer.setCreatedAt(dateGen.genDate());

        Merchant m = merchantRepository.getPassword(merchantUsername);

        if(m == null || m.getMerchantKey() == null  || m.getMerchantKey().getMerchantId() == null){
            response.setSuccess(false);
            response.setMessage("Error occured while creating the offer");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        offer.setMerchantId(m.getMerchantKey().getMerchantId());
        offer.setMerchantUsername(merchantUsername);

        try{
            System.out.println(offer);
            Offer createdOffer = offerRepository.save(offer);
            System.out.println("offer: "+createdOffer);
            if(createdOffer == null){
                response.setSuccess(false);
                response.setMessage("No such offer");
                response.setResponseObject(createdOffer);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {
                response.setSuccess(true);
                response.setMessage("Offer created successfully");
                response.setResponseObject(createdOffer);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex){

            response.setSuccess(false);
            response.setMessage("Error occured while creating the offer");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> getOffer(String merchantUsername,int offerId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            Offer offers = offerRepository.findOfferByMerchantUsernameAndOfferId(merchantUsername,offerId);
            if(offers == null){
                response.setSuccess(false);
                response.setMessage("No such offer");
                response.setResponseObject(offers);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            response.setSuccess(true);
            response.setResponseObject(offers);
            response.setMessage("Successfully fetched the offer");
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);

        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setResponseObject(new String(ex.getMessage()));
            response.setMessage("Error occured while fetching object");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    public  ResponseEntity<Response> getAllOffers(String merchantUsername){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            List<Offer> offers = offerRepository.findOfferByMerchantUsername(merchantUsername);
            response.setSuccess(true);
            response.setResponseObject(offers);
            response.setMessage("Successfully fetched the offers");
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

    public  ResponseEntity<Response> deleteOffer(String merchantUsername,int offerId){

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            int id = offerRepository.deleteOfferByMerchantUsernameAndOfferId(merchantUsername,offerId);
            if(id > 0){
                response.setSuccess(true);
                response.setMessage("Successfully deleted the offer");
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
