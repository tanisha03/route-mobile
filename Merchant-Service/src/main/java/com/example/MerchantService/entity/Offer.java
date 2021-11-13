package com.example.MerchantService.entity;

import com.example.MerchantService.util.OfferType;
import lombok.*;

import javax.persistence.*;

/**
 * Offer Entity - This describes the schema of the offers created by the merchant.
 *
 * 1. offerId
 * 2. offerName
 * 3. offerDetails
 * 5. createdAt
 * 6. validTill
 * 7. offerType - Festive/promotional/Clearance
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Offer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerId;

    @Column(name = "offername")
    private String offerName;

    @Column(name = "offerdetails")
    private String offerDetails;

    @Column(name = "createdat")
    private String createdAt;

    @Column(name = "validtill")
    private String validTill;

    @Column(name = "offertype")
    private OfferType offerType;

    @Column(name = "mid")
    private String merchantId;

    @Column(name = "musername")
    private String merchantUsername;
}
