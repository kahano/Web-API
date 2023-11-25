package com.project.custom_product.mapper;

import java.util.List;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.entities.Customer;


public interface customer_productMapper {

    List<customer_ProductDTO> getAll_customerProducts();


    default customer_ProductDTO to_customerProductDTO(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'to_customerProductDTO'");
    }

    Customer toCustomer(customer_ProductDTO customerDTO);
    
}
