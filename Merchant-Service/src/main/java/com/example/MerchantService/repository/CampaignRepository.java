package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign,Integer> {

    List<Campaign> findCampaignByMerchantUsername(String merchantUsername);
}
