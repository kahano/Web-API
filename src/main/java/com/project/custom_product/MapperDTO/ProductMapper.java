package com.project.custom_product.MapperDTO;

import org.springframework.stereotype.Component;

import com.project.custom_product.DTO.customerDTO;
import com.project.custom_product.DTO.productDTO;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;

@Component
public class ProductMapper {
    
     public productDTO convertProductToDto(Product product){
        
        return productDTO.builder()
        .product_name(product.getProduct_name())
        .category(product.getCategory())
        .serial_code(product.getSerial_code())
        .total_quantities(product.getTotal_quantities())
        .build();
    }

     public Product converProducDto_TOProduct(productDTO product){
        
        return Product.builder()
        .product_name(product.getProduct_name())
        .category(product.getCategory())
        .serial_code(product.getSerial_code())
        .total_quantities(product.getTotal_quantities())
        .build();
    }

   
}
