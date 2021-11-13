package com.example.CustomerService.service;

import com.example.CustomerService.entity.Subscriber;
import com.example.CustomerService.repository.SubscriberRepository;
import com.example.CustomerService.util.Config;
import com.example.CustomerService.util.MerchantView;
import com.example.CustomerService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    RestTemplate restTemplate;


    public ResponseEntity<Response> addSubscription(Subscriber subscriber){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        String url = Config.merchantServiceUrl;
        url = url + "/merchant/exists/"+subscriber.getMerchantUsername();
        ResponseEntity<Response> apiResponse = restTemplate.getForEntity(url,Response.class);
        if(apiResponse.getBody().isSuccess()){
            Subscriber createdSubscriber = subscriberRepository.save(subscriber);
            if(createdSubscriber.getSubsId() > 0){
                response.setSuccess(true);
                response.setMessage("subscription created successfully");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{
                response.setSuccess(false);
                response.setMessage("could not found the merchant");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            response.setSuccess(false);
            response.setMessage("could not found the merchant");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    public ResponseEntity<Response> getOffersByMerchant(String username){
        String url = Config.merchantServiceUrl;
        url = url + "/offer/"+username;

        ResponseEntity<Response> apiResponse = restTemplate.getForEntity(url,Response.class);
        return apiResponse;
    }

    public ResponseEntity<Response> getMerchants(String custEmail){
        String url = Config.merchantServiceUrl;
        url = url + "/merchant/all";

        ResponseEntity<Response> apiResponse = restTemplate.getForEntity(url,Response.class);
        ResponseEntity<Response> responseEntity;
        Response response = new Response();
        List<Object> merchants = new ArrayList<>();
        if(apiResponse.getBody().isSuccess()){
            Object obj = apiResponse.getBody().getResponseObject();
            List<LinkedHashMap> respMerch = (List<LinkedHashMap>) obj;
            if(respMerch != null && respMerch.size() > 0){

                for(LinkedHashMap o: respMerch){
                    HashMap<String,Object> hMap = new HashMap<>();
                    hMap.put("merchant",o);
                    boolean res = subscriberRepository.existsByCustEmailAndMerchantUsername(custEmail, (String) o.get("username"));
                    hMap.put("subscribed",res);
                    merchants.add(hMap);
                }
                response.setSuccess(true);
                response.setMessage("data fetched successfully");
                response.setResponseObject(merchants);
                responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
            }
            else{
                response.setSuccess(false);
                response.setMessage("Can't fetch merchants, some error occurred");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
        }
        else{
            response.setSuccess(false);
            response.setMessage("Can't fetch merchants, some error occurred");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return  responseEntity;
    }

}
