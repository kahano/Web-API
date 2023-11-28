package com.project.custom_product.Respository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.custom_product.entities.Purchase;

import jakarta.transaction.Transactional;


@Repository
public interface purchase_repository extends CrudRepository<Purchase,Integer> {

    Optional<Purchase> findByCustomerIdAndProductId(Integer customer_id, Integer product_id);
   

    List<Purchase> findByCustomerId(Integer customer_id);

    List<Purchase> findByProductId(Integer product_id);

    @Transactional
    void deletePurchaseByCustomerIdAndProductId(Integer customer_id, Integer product_id);
    
}
