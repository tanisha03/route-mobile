package com.example.MerchantService.entity;

import com.example.MerchantService.util.PurchaseCategory;
import lombok.*;

import javax.persistence.*;
import java.net.URL;

/**
 * 1. CampaignId
 * 2. CampaignName
 * 3. CampaignMessage
 * 4. Campaign Banner URL
 * 5. Campaign UserPurchaseCategory
 * 6. Campaign validTill
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Campaign {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignId;

    @Column(name = "campname")
    private String campaignName;

    @Column(name = "campmsg")
    private String campaignMessage;

    @Column(name = "campbannerurl")
    private URL campaignBannerUrl;

    @Column(name = "campcreatedat")
    private String campaignCreatedAt;

    @Column(name = "targetcategory")
    private PurchaseCategory targetCategory;

    @Column(name="mid")
    private String merchantId;

    @Column(name="musername")
    private String merchantUsername;

    @Transient
    private double campaignSuccessRate;

}
