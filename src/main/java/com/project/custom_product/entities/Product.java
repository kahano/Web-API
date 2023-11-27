package com.project.custom_product.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name = "product_id")
    private Integer product_id ;

    @Nonnull
    @Column(name = "product_name" , nullable = false)
    private String product_name;


    @Column(name = "category")
    private String category;

    
    @Nonnull
    @Column(name =  "serial_code", nullable = false, unique = true)
    private String serial_code;
    
    @Nonnull
    @Column(name = "total_quantities", nullable = false)
    private Integer total_quantities;


    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL )
    private List<Purchase> purchases ;

}
