package com.example.CustomerService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * We will have a table which will have all the subscriptions a user has
 * 1. Id
 * 2. customer email.
 * 3, merchantUsername.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subsid")
    @Id
    private int subsId;

    @Column(name = "custemail")
    private String custEmail;

    @Column(name = "merchusername")
    private String merchantUsername;
}
