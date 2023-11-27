package com.project.custom_product.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.custom_product.entities.Purchase;

public interface purchase_repository extends JpaRepository<Purchase,Integer> {
    
}
