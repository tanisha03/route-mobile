package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findOfferByMerchantUsername(String merchantName);
    Offer findOfferByMerchantUsernameAndOfferId(String merchantUsername,int offerId);

    @Transactional
    int deleteOfferByMerchantUsernameAndOfferId(String merchantUsername,int offerId);
}
