package com.project.custom_product.Service.Service_Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Respository.purchase_repository;
import com.project.custom_product.Service.DoesExist;
import com.project.custom_product.Service.Purchase_service;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;
import com.project.custom_product.entities.Purchase;
import com.project.custom_product.exception.PurchaseNotFoundExcpetion;

@Service
public class Purchase_serviceImpl implements Purchase_service {

  
    private purchase_repository purchase_repository;

    private customer_repository customer_repository;
    
    private product_repository product_repository;

   
    

    // @Autowired
    // private DoesExist<Customer> check_forCustomer;

    // @Autowired
    // private DoesExist<Product> check_forProduct;
    // //private DoesExist<Product> check_forProduct;

    // @Autowired
    // private DoesExist<Purchase> check_forPurchase;



    @Override
    public Purchase savePurchase(Purchase purchase, Integer customer_id, Integer product_id) {

        Customer customer =  Customer_serviceImpl.DoesObjectExist(customer_repository.findById(customer_id),customer_id);
        Product product =   Product_serviceImpl.DoesObjectExist(product_repository.findById(product_id), product_id);

        purchase.setCustomer(customer);
        purchase.setProduct(product);

        return purchase_repository.save(purchase);

       
    }

    @Override
    public Purchase findPurchaseBYId(Integer customer_id, Integer product_id) {
        
        Optional<Purchase> purchase = purchase_repository.findByCustomerId_and_ProductId(customer_id, product_id);
        return this.DoesObjectExist(purchase,customer_id, product_id);
    }

    @Override
    public Purchase updatePurchase(Integer customer_id, Integer product_id,Integer total_quantites) {
        
        Optional<Purchase> purchase = purchase_repository.findByCustomerId_and_ProductId(customer_id, product_id);
        Product product =   Product_serviceImpl.DoesObjectExist(product_repository.findById(product_id), product_id);
        product.setTotal_quantities(total_quantites);
        Purchase checkPurchase = DoesObjectExist(purchase, customer_id, product_id);
        checkPurchase.setProduct(product);
        return checkPurchase;
    }

    @Override
    public void deletePurchase(Integer customer_id, Integer product_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePurchase'");
    }

    @Override
    public Integer getTotalPricePurchase_per_Customer(  Integer customer_id,
            Integer product_id) {
        
        Integer sum = 0;
        Optional<Purchase> purchase = purchase_repository.findByCustomerId_and_ProductId(customer_id, product_id);
        Purchase checkPurchase = DoesObjectExist(purchase, customer_id, product_id);
        while(checkPurchase.getTotal_quantities() > 0){
            sum += checkPurchase.getPrice() * checkPurchase.getTotal_quantities();
        }

        return sum;


       
    }

    @Override
    public List<Purchase> getAllCustomersPurchased(Integer customer_id) {
    
        
        return purchase_repository.findPurchaseByCustomerId(customer_id);
    }

    @Override
    public List<Purchase> getAllProductssPurchased(Integer product_id) {
       
        return purchase_repository.findPurchaseByProductId(product_id);
    }
    
    Purchase DoesObjectExist(Optional<Purchase> object, Integer customer_id, Integer product_id){

        if(object.isPresent()){
            return object.get();
        }
        else{
            throw new PurchaseNotFoundExcpetion(customer_id, product_id);
        }
    }

    @Override
    public List<Purchase> getAllPurchases() {
       
        return (List<Purchase>) purchase_repository.findAll();
    }


}
