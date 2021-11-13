package com.example.CustomerService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * This is the class that specifies the data we are collecting from the customer.
 *
 * 1. Name
 * 2. Email
 * 3. Password
 * 4. Mobile No
 * 5. City
 * 6. Pincode
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Customer {

    @Id
    @Column(name="email")
    private String email;

    @Column(name = "password",length = 1000)
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private int pincode;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Subscriber.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "custemail")
    })
    private List<Subscriber> merchantsSubscribed;

}

