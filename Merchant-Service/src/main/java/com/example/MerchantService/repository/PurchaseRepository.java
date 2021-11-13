package com.example.MerchantService.repository;

import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.entity.Purchase;
import com.example.MerchantService.util.CampaignData;
import com.example.MerchantService.util.RankData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;



public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

    List<Purchase> findPurchaseByMerchantUsername(String merchantName);
    Purchase findPurchaseByMerchantUsernameAndPurchaseId(String merchantUsername,int purchaseId);

    @Transactional
    int deletePurchaseByMerchantUsernameAndPurchaseId(String merchantUsername,int purchaseId);

    @Query(value = "select musername,avg(purchaseamt) as avgSales from purchase group by musername order by avgSales DESC;",nativeQuery = true)
    List<RankData> getMerchantsWithRank();

    @Query(value = "select purchasecustnum from purchase where purchasecat=:purchaseCatId and musername=:mUsername",nativeQuery = true)
    List<CampaignData> getTargetAudience(String mUsername,int purchaseCatId);

    @Query(value = "select sum(purchaseamt) from purchase where musername=:mUsername",nativeQuery = true)
    double getSumPurchases(String mUsername);
}
