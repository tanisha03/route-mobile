package com.example.CustomerService.util;


import java.io.IOException;

import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.entity.Subscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
public class GenerateJson {
    public static void main(String[] a)
    {
        // Creating object of Product
        Customer customer = new Customer();
        Subscriber subscriber = new Subscriber();

        // Creating Object of ObjectMapper define in Jackson API
        ObjectMapper Obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStr = Obj.writeValueAsString(customer);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);

            // Converting the Java object into a JSON string
            jsonStr = Obj.writeValueAsString(subscriber);
            // Displaying Java object into a JSON string
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Getting data that we want to insert into an object
    // Getting data that we want to insert into an object
    public static Subscriber getObjectData(Subscriber subscriber)
    {

        return subscriber;
    }
    public static Customer getObjectData(Customer customer)
    {

        return customer;
    }
}

