package com.project.custom_product.Service.Service_Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Service.Customer_service;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.exception.CustomerNotFoundException;


@Service

public class Customer_serviceImpl implements Customer_service  {

    @Autowired
    private customer_repository customer_repos;




    @Override
    public Customer saveCustomer(Customer customer) {

        return customer_repos.save(customer);
    }



    @Override
    public void deleteCustomerById(Integer customer_id) {
        
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
     

        Optional<Customer> customer = customer_repos.findById(customer_id);
        Customer check =  DoesObjectExist(customer, customer_id);
        return check;
    }

    static Customer DoesObjectExist(Optional<Customer> object, Integer id){

        if(object.isPresent()){
            return object.get();
        }
        else{
            throw new CustomerNotFoundException(id);
        }
    }




    @Override
    public List<Customer> getAllcustomers() {
     
        
        return (List<Customer>)customer_repos.findAll();
    }

    // private Integer findIndexById(Integer customer_id){

    //     return IntStream.range(0, customer_repos.findAll().size())
    //             .filter(index -> getAllcustomers().get(index).getCustomer_id() == (customer_id))
    //             .findFirst()
    //             .orElseThrow();


    // }



   


    
    
}
