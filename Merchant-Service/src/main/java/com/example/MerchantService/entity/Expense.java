package com.example.MerchantService.entity;

import com.example.MerchantService.util.ExpenseCategory;
import lombok.*;

import javax.persistence.*;

/**
 * We log down the merchants expenses.
 *
 * 1. ExpenseId
 * 2. ExpenseName
 * 3. ExpenseDescription.
 * 4. ExpenseAmount
 * 5. ExpenseCategory
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter@ToString
public class Expense {

    @Id
    @Column(name="id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;

    @Column(name = "expname")
    private String expenseName;

    @Column(name = "expdesc")
    private String expenseDescription;

    @Column(name = "createdat")
    private String createdAt;

    @Column(name = "expamt")
    private double expenseAmount;

    @Column(name = "expcat")
    private ExpenseCategory expenseCategory;

    @Column(name="mid")
    private String merchantId;

    @Column(name="musername")
    private String merchantUsername;


}
