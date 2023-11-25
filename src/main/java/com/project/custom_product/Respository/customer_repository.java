package com.project.custom_product.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.custom_product.entities.Customer;

@Repository
public interface customer_repository extends JpaRepository<Customer,Integer> {
    
}
