package com.project.custom_product.Respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.custom_product.entities.Purchase;

import jakarta.transaction.Transactional;

@Repository
public interface purchase_repository extends JpaRepository<Purchase,Integer> {

    Optional<Purchase> findByCustomerId_and_ProductId(Integer student_id, Integer product_id);

    List<Purchase> findPurchaseByCustomerId(Integer id);

    List<Purchase> findPurchaseByProductId(Integer id);

    @Transactional
    void deletePurchaseBy_CustomerId_and_ProductId(Integer custom_id, Integer prod_id);
    
}
