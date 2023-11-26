package com.project.custom_product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.entities.Customer;


public interface service {

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(Integer id);

    Customer updateCustomer(Integer id, Customer customer);

    
    Customer findCustomerById(Integer id);

    List<Customer> getAllcustomers();







    




    
}
