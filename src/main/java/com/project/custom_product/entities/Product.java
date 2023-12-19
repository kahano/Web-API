package com.project.custom_product.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @Nonnull
    @Column(name = "product_name" , nullable = false)
    private String product_name;



    @Column(name = "category",nullable = false)
    private String category;

    

    @Column(name =  "serial_code",nullable = false, unique = true)
    private String serial_code;
    
    @Nonnull
    @Column(name = "total_quantities", nullable = false)
    private Integer total_quantities;


    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL )
    private List<Purchase> purchases ;

    

}
