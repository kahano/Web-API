package com.project.custom_product.entities;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id" , nullable = false)
    private Integer purchase_id;

    @Nonnull
    @Column(name = "purchase_code", nullable = false)
    private String purchase_code;

    @Nonnull
    @Column(name = "price" , nullable = false)
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;


    
}
