package com.example.MerchantService.entity;

import com.example.MerchantService.util.StoreType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import java.math.BigInteger;

public interface MerchantView {

        String getId();
        String getUsername();
        String getPhone();
        String getUpi();
        String getStoreName();
        String getEmail();
        String getAddress();
        String getOwnerName();
        int getPincode();
        int getStaffCount();

}
