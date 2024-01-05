package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Service.Customer_service;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.exception.CustomerNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class Customer_serviceImpl implements Customer_service  {

  
    private final customer_repository customer_repos;

    


    @Override
    public Customer saveCustomer(Customer customer) {

        return customer_repos.save(customer);
    }



    @Override
    public void deleteCustomerById(Integer customer_id)  {
        
        Customer customer = customer_repos.findById(customer_id).get();
        customer_repos.delete(customer);
    }


     @Override
    public Customer updateCustomer(Integer customer_id, Customer customer) {
             
        Customer updated_customer = findCustomerById(customer_id);
        updated_customer.setFirst_name(customer.getFirst_name());
        updated_customer.setLast_name(customer.getLast_name());
        updated_customer.setAddress(customer.getAddress());
        updated_customer.setTelefon_number(customer.getTelefon_number());
        return customer_repos.save(updated_customer);

    }
    

    @Override
    public Customer findCustomerById(Integer customer_id) {
     

        return customer_repos.findById(customer_id).
            orElseThrow(()-> new CustomerNotFoundException(customer_id));
       
    }

   

    @Override
    public List<Customer> getAllcustomers() {
     
        
        return (List<Customer>)customer_repos.findAll();
    }

    



   


    
    
}
