package com.example.MerchantService.controller;

import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.service.CampaignService;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignController {


    @Autowired
    CampaignService campaignService;

    @PostMapping("/campaign/{merchantName}")
    public ResponseEntity<Response> createCampaign(@PathVariable("merchantName") String merchantName, @RequestBody Campaign campaign){
        return campaignService.createCampaign(campaign,merchantName);
    }

    @GetMapping("/campaign/{merchantName}")
    public ResponseEntity<Response> getAllCampaigns(@PathVariable("merchantName") String merchantName){
        return campaignService.getAllCampaigns(merchantName);
    }

}
