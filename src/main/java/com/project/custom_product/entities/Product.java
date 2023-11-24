package com.project.custom_product.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prod_id ;

    @Column(name = "product_name" , nullable = false)
    private String productname;

    @Column(name = "category",nullable = false)
    private String category;
    
    @Column(name = "total_quantities", nullable = false)
    private Integer total_quantites;

}
