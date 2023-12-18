package com.project.custom_product.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class productDTO {

    @JsonIgnore
    private Integer id;

    private String product_name;
    private String category;
    private String serial_code;
    private Integer total_quantities;
    
}
