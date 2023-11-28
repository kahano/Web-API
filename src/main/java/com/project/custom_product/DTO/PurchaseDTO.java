package com.project.custom_product.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {

    @JsonIgnore
    private Integer purchase_id;

    private String purchase_code;

    private Integer price;

    private Integer total_quantities;

    private Integer bill;

   

    
    
}
