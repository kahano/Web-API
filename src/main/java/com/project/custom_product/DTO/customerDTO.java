package com.project.custom_product.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class customerDTO{

    @JsonIgnore
    private Integer customer_id;

    private String first_name;
    private String last_name;
    private String address;
    private String telefon_number;
    // private Integer product_id;
    // private String product_name;
    // private Integer total_quantities;

   
    
}
