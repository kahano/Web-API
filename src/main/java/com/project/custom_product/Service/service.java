package com.project.custom_product.Service;

import java.util.List;


import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.entities.Customer;

public interface service {

    customer_ProductDTO saveCustomer(customer_ProductDTO customerdto);

    customer_ProductDTO deleteCustomerById(long id);

    customer_ProductDTO updateCustomer(Customer customer);

    //List<customer_ProductDTO> getAll_customerProducts();

    customer_ProductDTO getCustomerById(long id);

    //Integer get_available_products();


    
}
