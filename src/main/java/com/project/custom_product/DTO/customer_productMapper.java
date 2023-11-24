package com.project.custom_product.DTO;

import java.util.List;

import com.project.custom_product.entities.Customer;

public interface customer_productMapper {

    List<customer_ProductDTO> getAll_customerProducts();

    Integer get_available_products();

    customer_ProductDTO to_customerProductDTO(Customer customer);

    Customer toCustomer(customer_ProductDTO customer_Prod);
    
}
