package com.example.MerchantService.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MerchantKey implements Serializable {

    @Column(name = "id",unique = true,nullable = false)
    private String merchantId;

    @Column(name = "username",unique = true,nullable = false)
    private String username;
}