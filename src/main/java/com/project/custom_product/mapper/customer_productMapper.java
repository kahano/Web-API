package com.project.custom_product.mapper;

import java.util.List;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.entities.Customer;


public interface customer_productMapper {

    List<customer_ProductDTO> getAll_customerProducts();


    customer_ProductDTO to_customerProductDTO(Customer customer);

    Customer toCustomer(customer_ProductDTO customer_Prod);
    
}
