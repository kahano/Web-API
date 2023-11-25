package com.project.custom_product.Service.Service_Impl;

import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Service.service;
import com.project.custom_product.entities.Customer;

@Service
@Primary
@Qualifier
public class serviceImpl implements service  {

    @Autowired
    private customer_repository customer_repos;






    @Override
    public Customer saveCustomer(Customer customer) {
        // TODO Auto-generated method stub
        return customer_repos.save(customer);
    }





    // @Override
    // public void deleteCustomerById(Integer id) {
    //     // TODO Auto-generated method stub
    //     customer_repos.findById(id);
    // }




    @Override
    public Integer get_available_products() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get_available_products'");
    }





    @Override
    public Optional<Customer> findCustomerById(Integer customer_id) {
        // TODO Auto-generated method stub
        
        return customer_repos.findById(customer_id);
    }





    @Override
    public void deleteCustomerById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomerById'");
    }

    
    
}
