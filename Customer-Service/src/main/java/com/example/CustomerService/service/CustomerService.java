package com.example.CustomerService.service;

import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.repository.CustomerRepository;
import com.example.CustomerService.util.Response;
import com.example.CustomerService.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Encryption encryption;

    public ResponseEntity<Response> registerCustomer(Customer customer) throws Exception {

        ResponseEntity responseEntity;
        Response response = new Response();

        Customer getCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
        System.out.println("find: "+getCustomer);
        if(getCustomer != null){
            response.setSuccess(false);
            response.setMessage("customer already exists, use a different mail id");
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
        Customer createdCustomer = customerRepository.save(customer);
        if(createdCustomer != null){
            createdCustomer.setPassword(null);
            response.setSuccess(true);
            response.setMessage("customer created successfully");
            response.setResponseObject(createdCustomer);
            responseEntity = new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            response.setResponseObject(null);
            response.setMessage("some error occurred while creating the customer");
            response.setSuccess(false);
            responseEntity = new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> loginCustomer(Customer customer) throws Exception {

        ResponseEntity responseEntity;
        Response response = new Response();

        if(customer == null || Objects.equals(customer.getEmail(), "") || Objects.equals(customer.getPassword(), "")){
            response.setSuccess(false);
            response.setMessage("Please provide both email and password");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
        else{
            Customer fetchedCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
            if(fetchedCustomer == null){
                response.setSuccess(false);
                response.setMessage("Invalid credentials");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity(response,HttpStatus.BAD_REQUEST);
                return responseEntity;
            }
            String fetchedPassword = fetchedCustomer.getPassword();
            if(fetchedCustomer != null && fetchedCustomer.getEmail().equals(customer.getEmail()) && fetchedPassword.equals(customer.getPassword())){
                response.setSuccess(true);
                response.setMessage("Login successful");
                fetchedCustomer.setPassword(null);
                response.setResponseObject(fetchedCustomer);
                responseEntity = new ResponseEntity(response,HttpStatus.OK);
            }
            else{
                response.setSuccess(false);
                response.setMessage("Invalid credentials");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity(response,HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;

    }

}
