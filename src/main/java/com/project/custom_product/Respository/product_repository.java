package com.project.custom_product.Respository;

import com.project.custom_product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;


public interface product_repository extends JpaRepository<Product,Integer> {
    
    @Query(
        value = "select * from product where product_name = :product_name ",
        nativeQuery = true

    )
    
    Optional<Product> existsByProduct_Name(String product_name);
}
