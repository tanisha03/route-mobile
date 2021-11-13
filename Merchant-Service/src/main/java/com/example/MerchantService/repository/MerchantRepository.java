package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Merchant;
import com.example.MerchantService.entity.MerchantKey;
import com.example.MerchantService.entity.MerchantView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, MerchantKey> {

    @Query(value = "select * from merchant where username=?1 ",nativeQuery = true)
    Merchant getPassword(String username);

    @Query(value = "select * from merchant where eligible_for_lending = true and username != ?1  ",nativeQuery = true)
    List<Merchant> getEligibleMerchants(String username);

    @Modifying
    @Transactional
    @Query(value = "update merchant set eligible_for_lending = true where username=?1",nativeQuery = true)
    void updateMerchantAsLender(String username);

    boolean existsMerchantByMerchantKey_Username(String username);

    @Query(value = "select id,username,address,email,ownername,phone,pincode,staffcount,storename,upi from merchant",nativeQuery = true)
    List<MerchantView> findAllMerchants();
}
