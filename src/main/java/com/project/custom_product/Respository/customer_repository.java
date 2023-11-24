package com.project.custom_product.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.custom_product.entities.Customer;

public interface customer_repository extends JpaRepository<Customer,Long> {
    
}
