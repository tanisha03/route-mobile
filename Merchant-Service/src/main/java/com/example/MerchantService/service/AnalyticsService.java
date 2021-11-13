package com.example.MerchantService.service;

import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.repository.CampaignRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.util.DummyDataGen;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * As of now hardcoded, to be populated from the database later.
 */
@Service
public class AnalyticsService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    DummyDataGen dummyDataGen;

    public ResponseEntity<Response> getSalesAnalytics(String merchantUsername){
        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        Merchant merchant = merchantRepository.getPassword(merchantUsername);
        if(merchant == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("Merchant Not found");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        response.setResponseObject(dummyDataGen.getDummySalesData());
        response.setSuccess(true);
        response.setMessage("Data fetched successfully");
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;

    }
    public ResponseEntity<Response> getProductSales(String merchantUsername){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();


        Merchant merchant = merchantRepository.getPassword(merchantUsername);
        if(merchant == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("Merchant Not found");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        response.setResponseObject(dummyDataGen.getDummySalesCategoryData());
        response.setSuccess(true);
        response.setMessage("Data fetched successfully");
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<Response> getProductSalesInsights(String merchantUsername){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();


        Merchant merchant = merchantRepository.getPassword(merchantUsername);
        if(merchant == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("Merchant Not found");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        response.setResponseObject(dummyDataGen.getDummyTopSellingItems());
        response.setSuccess(true);
        response.setMessage("Data fetched successfully");
        responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;

    }
    public ResponseEntity<Response> getCampaignSuccessRate(String merchantUsername){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        Merchant merchant = merchantRepository.getPassword(merchantUsername);
        if(merchant == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("Merchant Not found");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        List<Campaign> campaigns = campaignRepository.findCampaignByMerchantUsername(merchantUsername);

        if(campaigns == null){
            response.setResponseObject(null);
            response.setSuccess(false);
            response.setMessage("No campaigns are created yet");
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        else{
            for(Campaign campaign: campaigns){
                int randomNum = ThreadLocalRandom.current().nextInt(20, 70 + 1);
                campaign.setCampaignSuccessRate(randomNum);
            }
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("data",campaigns);
            hashMap.put("graph","bar");
            hashMap.put("info","This can be a bar graph showing the success of each campaign");
            response.setMessage("Data fetched successfully");
            response.setSuccess(true);
            response.setResponseObject(hashMap);

            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }

}
