package com.project.custom_product.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private long customer_id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

     @Column(name = "last_name", nullable = false)
    private String last_name;


    private String password;

    @Column(name = "adress")
    private String address;

    @Column(name = "telefon_number")
    private String telefon_number;

    @ManyToOne()
    @JoinColumn(name = "prod_id")
    private Product product;
}
