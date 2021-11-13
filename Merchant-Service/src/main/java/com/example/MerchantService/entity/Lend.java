package com.example.MerchantService.entity;

import lombok.*;

import javax.persistence.*;

/**
 * This table basically maintains the loans given by merchants.
 *
 * 1. loanId
 * 2. loanGivenTo - merchantUsername.
 * 3. loanAmount
 * 4. loanDate
 * 5. Foreign key to the merchants table.
 * 6. Approved
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Lend {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lendId;

    @Column(name = "loanRequestedFrom")
    private String loanRequestedFrom;

    @Column(name = "loanRequestedBy")
    private String loanRequestedBy;

    @Column(name = "loanAmount")
    private double loanAmount;

    @Column(name = "loanDate")
    private String loanDate;

    @Column(name = "isLoanGiven")
    private boolean isLoanGiven;

    public Lend(String loanRequestedFrom, String loanRequestedBy, double loanAmount, String loanDate, boolean isLoanGiven) {
        this.loanRequestedFrom = loanRequestedFrom;
        this.loanRequestedBy = loanRequestedBy;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.isLoanGiven = isLoanGiven;
    }
}
