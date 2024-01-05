package com.project.custom_product.Service;

import java.util.List;

import com.project.custom_product.entities.Purchase;

import jakarta.transaction.Transactional;

public interface Purchase_service {
    
    Purchase savePurchase(Purchase purchase, Integer customer_id, Integer product_id);

    Purchase findPurchaseBYId(Integer customer_id, Integer product_id);


    Purchase updatePurchase( Integer customer_id, Integer product_id, Integer total_quantities);

    void delete(Integer customer_id, Integer product_id);

    List<Purchase> getAllCustomersPurchased(Integer customer_id);

    List<Purchase> getAllProductssPurchased(Integer product_id);

    List<Purchase> getAllPurchases();

    
}
