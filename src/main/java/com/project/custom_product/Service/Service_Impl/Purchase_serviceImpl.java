package com.project.custom_product.Service.Service_Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Respository.purchase_repository;

import com.project.custom_product.Service.Purchase_service;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;
import com.project.custom_product.entities.Purchase;
import com.project.custom_product.exception.PurchaseNotFoundExcpetion;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor

@Service
public class Purchase_serviceImpl implements Purchase_service {


     purchase_repository purchase_repos;


     Customer_serviceImpl customer_service;


     Product_serviceImpl product_service;

  

   
    

    // @Autowired
    // private DoesExist<Customer> check_forCustomer;

    // @Autowired
    // private DoesExist<Product> check_forProduct;
    // //private DoesExist<Product> check_forProduct;

    // @Autowired
    // private DoesExist<Purchase> check_forPurchase;



    @Override
    public Purchase savePurchase(Purchase purchase, Integer customer_id, Integer product_id) {

        Customer customer =  customer_service.findCustomerById(customer_id);
        Product product =  product_service.findProductById(product_id);

        purchase.setCustomer(customer);
        purchase.setProduct(product);

        return purchase_repos.save(purchase);

       
    }

    @Override
    public Purchase findPurchaseBYId(Integer customer_id, Integer product_id) {
        
        Optional<Purchase> purchase = purchase_repos.findByCustomerIdAndProductId(customer_id, product_id);
        return DoesObjectExist(purchase,customer_id, product_id);
       
    }

    @Override
    public Purchase updatePurchase(Integer customer_id, Integer product_id,Integer total_quantites) {
        
        // Optional<Purchase> purchase = purchase_repository.findByCustomerId_and_ProductId(customer_id, product_id);
        // Product product =   Product_serviceImpl.DoesObjectExist(product_repository.findById(product_id), product_id);
        // product.setTotal_quantities(total_quantites);
        // Purchase checkPurchase = DoesObjectExist(purchase, customer_id, product_id);
        // checkPurchase.setProduct(product);
        return null;
    }

    @Override
    public void deletePurchase(Integer customer_id, Integer product_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePurchase'");
    }

    @Override
    public Purchase getTotalPricePurchase_per_Customer(  Integer customer_id, Integer product_id) {
        
        Integer sum = 0;
        Optional<Purchase> purchase = purchase_repos.findByCustomerIdAndProductId(customer_id, product_id);
        Purchase checkPurchase = DoesObjectExist(purchase, customer_id, product_id);
        if(checkPurchase.getTotal_quantities() > 0){
            sum += checkPurchase.getPrice() * checkPurchase.getTotal_quantities();
        }
        checkPurchase.setBill(sum);
       

        return checkPurchase;
   


       
    }

    @Override
    public List<Purchase> getAllCustomersPurchased(Integer customer_id) {
    
        
        // 
        return null;
    }

    @Override
    public List<Purchase> getAllProductssPurchased(Integer product_id) {
       
        // return purchase_repository.findPurchaseByProductId(product_id);
        return null;
    }
    
    static Purchase DoesObjectExist(Optional<Purchase> object, Integer customer_id, Integer product_id){

        if(object.isPresent()){
            return object.get();
        }
        else{
            throw new PurchaseNotFoundExcpetion(customer_id, product_id);
        }
    }

    @Override
    public List<Purchase> getAllPurchases() {
       
        // return (List<Purchase>) purchase_repository.findAll();
        return null;
    }


}
