package com.project.custom_product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.entities.Customer;


public interface service {

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(long id);

    
    Optional<Customer> findCustomerById(Integer id);

    List<customer_ProductDTO> getAllcustomers();

    





    




    
}
