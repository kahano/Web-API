package com.project.custom_product.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.custom_product.entities.Product;

@Repository
public interface product_repository extends JpaRepository<Product,Integer> {
    
}
