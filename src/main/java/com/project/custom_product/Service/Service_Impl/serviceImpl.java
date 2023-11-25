package com.project.custom_product.Service.Service_Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.custom_product.DTO.customer_ProductDTO;
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





    @Override
    public void deleteCustomerById(Integer id) {
        // TODO Auto-generated method stub
        Customer customer = customer_repos.findById(id).get();
        customer_repos.delete(customer);
    }

     @Override
    public Customer updateCustomer(Integer customer_id, Customer customer) {
        // TODO Auto-generated method stub

        return customer_repos.findAll().set(findIndexById(customer_id), customer);

    }
        




    @Override
    public Optional<Customer> findCustomerById(Integer customer_id) {
        // TODO Auto-generated method stub

        Optional<Customer> customer = customer_repos.findById(customer_id);
        
        return customer;
    }



    @Override
    public List<Customer> getAllcustomers() {
        // TODO Auto-generated method stub
        
        return customer_repos.findAll();
    }


    private Integer findIndexById(Integer customer_id){

        return IntStream.range(0, customer_repos.findAll().size())
                .filter(index -> getAllcustomers().get(index).getCustomer_id() == (customer_id))
                .findFirst()
                .orElseThrow();


    }



   


    
    
}
