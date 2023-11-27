package com.project.custom_product.Service;

import java.util.List;

import com.project.custom_product.entities.Customer;


public interface Customer_service {

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(Integer id);

    Customer updateCustomer(Integer id, Customer customer);

    
    Customer findCustomerById(Integer id);

    List<Customer> getAllcustomers();







    




    
}
