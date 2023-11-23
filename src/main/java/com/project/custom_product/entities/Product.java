package com.project.custom_product.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Product {


    private long id ;
    private String name;
    private String category;

    private Integer total_quantites;



}
