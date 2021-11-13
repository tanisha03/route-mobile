package com.example.MerchantService.service;

import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.repository.CampaignRepository;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.repository.PurchaseRepository;
import com.example.MerchantService.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    DateGen dateGen;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    WhatsAppCommunication whatsAppCommunication;

    public ResponseEntity<Response> createCampaign(Campaign campaign,String merchantUsername){
        
        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        campaign.setCampaignCreatedAt(dateGen.genDate());

        Merchant m = merchantRepository.getPassword(merchantUsername);

        if(m == null || m.getMerchantKey() == null  || m.getMerchantKey().getMerchantId() == null){
            response.setSuccess(false);
            response.setMessage("Error occured while creating campaign");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        campaign.setMerchantId(m.getMerchantKey().getMerchantId());
        campaign.setMerchantUsername(merchantUsername);

        try{
            Campaign createdCampaign = campaignRepository.save(campaign);

            if(createdCampaign == null){
                response.setSuccess(false);
                response.setMessage("Campaign could not be created.");
                response.setResponseObject(createdCampaign);
                responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else {
                //send campaign.

                List<CampaignData> customerNumbers = purchaseRepository.getTargetAudience(m.getMerchantKey().getUsername(),campaign.getTargetCategory().getValue());

                boolean response1 = whatsAppCommunication.sendWhatsappText(customerNumbers,campaign);
                boolean response2 = whatsAppCommunication.sendWhatsappMedia(customerNumbers,campaign);

                if(!response1 || !response2){
                    response.setSuccess(false);
                    response.setMessage("Some error occured while sending campaigns, but campaign is saved");
                    response.setResponseObject(createdCampaign);
                    responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    return responseEntity;
                }

                response.setSuccess(true);
                response.setMessage("Campaign created and sent successfully.");
                response.setResponseObject(createdCampaign);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex){
            response.setSuccess(false);
            response.setMessage("Error occured while creating the campaign");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        }
        return responseEntity;
    }

    public  ResponseEntity<Response> getAllCampaigns(String merchantUsername){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            List<Campaign> campaign = campaignRepository.findCampaignByMerchantUsername(merchantUsername);
            response.setSuccess(true);
            response.setResponseObject(campaign);
            response.setMessage("Successfully fetched the campaign");
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
    
}
