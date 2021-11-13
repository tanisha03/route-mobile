package com.example.MerchantService.util;

import com.example.MerchantService.entity.Campaign;
import com.example.MerchantService.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DummyDataGen {

    @Autowired
    CampaignRepository campaignRepository;

    public HashMap<String,Object> getDummySalesData(){
        HashMap<String,Object> salesData = new HashMap<>();
        HashMap<String,Integer> hMap = new HashMap<>();
        hMap.put("January",12000);
        hMap.put("February",11000);
        hMap.put("March",13000);
        hMap.put("April",15000);
        hMap.put("May",16000);
        hMap.put("June",14500);
        hMap.put("July",17000);
        hMap.put("August",13000);
        hMap.put("September",15000);
        hMap.put("October",21000);
        hMap.put("November",19000);
        hMap.put("December",25000);

        salesData.put("data",hMap);
        salesData.put("graph","bar");
        salesData.put("info","This bar graph shows sales during each month of the merchant");
        return salesData;
    }

    public HashMap<String,Object> getDummySalesCategoryData(){
        HashMap<String,Object> salesData = new HashMap<>();
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("GROCERY",23);
        hashMap.put("HOMEUSE",32);
        hashMap.put("UTILITY",19);
        hashMap.put("FOOD",26);
        salesData.put("data",hashMap);
        salesData.put("graph","pie");
        salesData.put("info","This pie chart tells component of percentage of sales of each category over time");
        return salesData;
    }

    public HashMap<String,Object> getDummyTopSellingItems(){
        HashMap<String,Object> salesData = new HashMap<>();

        HashMap<String,Integer> hashMap1 = new HashMap<>();
        HashMap<String,Integer> hashMap2 = new HashMap<>();
        HashMap<String,Integer> hashMap3 = new HashMap<>();
        HashMap<String,Integer> hashMap4 = new HashMap<>();

        hashMap1.put("your-share",21);
        hashMap1.put("nearby-share",79);

        hashMap2.put("your-share",25);
        hashMap2.put("nearby-share",75);

        hashMap3.put("your-share",45);
        hashMap3.put("nearby-share",55);

        hashMap4.put("your-share",34);
        hashMap4.put("nearby-share",66);

        HashMap<String,Object> sData = new HashMap<>();

        sData.put("GROCERY",hashMap1);
        sData.put("UTILITY",hashMap2);
        sData.put("FOOD",hashMap3);
        sData.put("HOMEUSE",hashMap4);

        salesData.put("data",sData);
        salesData.put("graph","pie");
        salesData.put("info","This can be a group of 4 pie charts tells shops market share with respect to nearby stores");
        return salesData;
    }
}
