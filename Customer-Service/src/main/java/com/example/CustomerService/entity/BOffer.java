package com.example.CustomerService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BOffer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name = "description")
    String description;

    @Column(name = "category")
    String category;

    @Column(name = "title")
    String title;

}
