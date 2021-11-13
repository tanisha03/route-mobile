package com.example.MerchantService.service;

import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.MerchantView;
import com.example.MerchantService.repository.MerchantRepository;
import com.example.MerchantService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: Remove code duplication, can be done by extracting the code inside catch
 *  * to a separate function.
 */
@Service
public class MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    public ResponseEntity<Response> registerMerchant(Merchant merchant){
        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        try{
            Merchant registeredMerchant = merchantRepository.save(merchant);
            response.setResponseObject(registeredMerchant);
            response.setMessage("Merchant Registered Successfully");
            response.setSuccess(true);
            responseEntity = new ResponseEntity<Response>(response, HttpStatus.OK);

        }
        catch(Exception e){
            response.setSuccess(false);
            response.setMessage("The username already exists in the database");
            response.setResponseObject(new String(e.getMessage()));
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public ResponseEntity<Response> loginMerchant(Merchant merchant) {

        Response response = new Response();
        ResponseEntity<Response> responseEntity;
        String username = merchant.getMerchantKey().getUsername();
        String receivedPassword = merchant.getPassword();
        if (username == null || receivedPassword == null) {
            response.setSuccess(false);
            response.setMessage("Username or password is null");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } else {
            Merchant fetchedMerchant = merchantRepository.getPassword(username);
            if (fetchedMerchant == null) {
                response.setSuccess(false);
                response.setMessage("User Does not exist");
                response.setResponseObject(fetchedMerchant);
                responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            else if (!fetchedMerchant.getPassword().equals(receivedPassword)) {
                response.setSuccess(false);
                response.setMessage("Invalid Password");
                response.setResponseObject(null);
                responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            } else {
                fetchedMerchant.setPassword("");
                response.setSuccess(true);
                response.setMessage("Login Successful");
                response.setResponseObject(fetchedMerchant);
                responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    public ResponseEntity<Response> checkMerchantExists(String username){

        ResponseEntity responseEntity;
        Response response = new Response();
        boolean exists = merchantRepository.existsMerchantByMerchantKey_Username(username);
        if(exists){
            response.setSuccess(true);
            response.setMessage("Merchant fetched successfully");
        }
        else{
            response.setSuccess(false);
            response.setMessage("Merchant does not exist");
        }
        response.setResponseObject(null);
        responseEntity = new ResponseEntity(response,HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<Response> getAllMerchants(){

        ResponseEntity<Response> responseEntity;
        Response response = new Response();

        List<MerchantView> merchantList = merchantRepository.findAllMerchants();

        if(merchantList.equals(null) || merchantList.size() == 0){
            response.setSuccess(false);
            response.setMessage("could not find merchants");
            response.setResponseObject(null);
            responseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            response.setSuccess(true);
            response.setMessage("fetched merchants successfully");
            response.setResponseObject(merchantList);
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }


}
