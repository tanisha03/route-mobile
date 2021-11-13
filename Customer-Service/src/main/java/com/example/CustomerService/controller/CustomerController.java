package com.example.CustomerService.controller;

import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.service.CustomerService;
import com.example.CustomerService.util.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String getResponse(){
        return "Tomorrow is Monday and back to work!!";
    }


    @PostMapping("/customer/create")
    public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/customer/login")
    public ResponseEntity<Response> loginCustomer(@RequestBody Customer customer) throws Exception {
        return customerService.loginCustomer(customer);
    }

}
