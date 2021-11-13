package com.example.MerchantService.util;

import java.io.IOException;

import com.example.MerchantService.entity.Campaign;
import com.fasterxml.jackson.databind.ObjectMapper;
public class GenerateJson {
    public static void main(String[] a)
    {
        // Creating object of Product
        Campaign merchant = new Campaign();

        // Creating Object of ObjectMapper define in Jackson API
        ObjectMapper Obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStr = Obj.writeValueAsString(merchant);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Getting data that we want to insert into an object
    public static Campaign getObjectData(Campaign merchant)
    {

        return merchant;
    }
}

