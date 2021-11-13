package com.example.MerchantService.entity;

import com.example.MerchantService.util.ExpenseCategory;
import com.example.MerchantService.util.PurchaseCategory;
import lombok.*;

import javax.persistence.*;

/**
 * This is the description for the Purchase Entity
 *
 * 1. PurchaseId
 * 2. PurchaseCategory
 * 3. PurchaseAmount
 * 4. PurchaseDate;
 * 5. PurchaseCustomerMobileNo;
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Purchase {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    @Column(name = "purchasecat")
    private PurchaseCategory purchaseCategory;

    @Column(name = "purchaseamt")
    private double purchaseAmount;

    @Column(name = "purchasedate")
    private String createdAt;

    @Column(name = "purchasecustnum")
    private String purchaseCustNum;

    @Column(name="mid")
    private String merchantId;

    @Column(name="musername")
    private String merchantUsername;

    @Column(name = "textmsgstatus")
    private String textMsgStatus;

}
