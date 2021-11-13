package com.example.MerchantService.entity;


import com.example.MerchantService.util.StoreType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Merchant Entity => This merchant entity defines all the fields related
 * to the merchant that will beprovided while signing up.
 *
 * 1. Id
 * 2. Username
 * 3. password
 * 4. email
 * 5. phone number.
 * 6. Extension code.
 * 7. Pin code
 * 8. Address
 * 9. Staff count
 * 10. Store Type
 *      => Grocery
 *      => Electornics
 *      => Clothing
 *      => HomeNeeds
 * 11. Store Name
 * 12. Owner Name
 * 13. UPI id
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Merchant {

    @EmbeddedId
    private MerchantKey merchantKey;

    @Column(name = "password")
    private String password;

    @Column(name = "storename")
    private String storeName;

    @Column(name = "ownername")
    private String ownerName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private BigInteger phoneNumber;

    @Column(name = "ext")
    private int phoneExtension;

    @Column(name = "pincode")
    private int pincode;

    @Column(name = "address")
    private String address;

    @Column(name = "staffcount")
    private int staffCount;

    @Column(name = "storetype")
    private StoreType storeType;

    @Column(name = "upi")
    private String upiId;

    @Column(name = "eligibleForLending")
    private boolean lender = false;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Offer.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "mid"),
            @JoinColumn(name = "musername")
    })
    private List<Offer> offers;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Expense.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "mid"),
            @JoinColumn(name = "musername")
    })
    private List<Offer> expenses;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Purchase.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "mid"),
            @JoinColumn(name = "musername")
    })
    private List<Offer> purchases;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Campaign.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "mid"),
            @JoinColumn(name = "musername")
    })
    private List<Campaign> campaigns;

}