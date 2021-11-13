package com.example.MerchantService.controller;

import com.example.MerchantService.service.AnalyticsService;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    @Autowired
    AnalyticsService analyticsService;

    @GetMapping("/analytics/sales/{mUsername}")
    public ResponseEntity<Response> getSalesAnalytics(@PathVariable("mUsername") String merchantUsername){
        return analyticsService.getSalesAnalytics(merchantUsername);
    }

    @GetMapping("/analytics/product-cat-sales/{mUsername}")
    public ResponseEntity<Response> getProductSales(@PathVariable("mUsername") String merchantUsername){
        return analyticsService.getProductSales(merchantUsername);
    }

    @GetMapping("/analytics/nearby-sales-insights/{mUsername}")
    public ResponseEntity<Response> getProductSalesInsights(@PathVariable("mUsername") String merchantUsername){
        return analyticsService.getProductSalesInsights(merchantUsername);
    }

    @GetMapping("/analytics/campaign-success-rate/{mUsername}")
    public ResponseEntity<Response> getCampaignSuccessRate(@PathVariable("mUsername") String merchantUsername){
        return analyticsService.getCampaignSuccessRate(merchantUsername);
    }

}
