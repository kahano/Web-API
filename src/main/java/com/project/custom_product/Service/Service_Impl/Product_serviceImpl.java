package com.project.custom_product.Service.Service_Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Service.DoesExist;
import com.project.custom_product.Service.Product_service;
import com.project.custom_product.entities.Product;

@Service
public class Product_serviceImpl implements Product_service {

    @Autowired
    private product_repository productRepository ;

 
    private DoesExist<Product> check_forObject;

    @Override
    public Product saveProduct(Product product) {
        
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {

        Optional<Product> product = productRepository.findById(id);
        return check_forObject.DoesObjectExist(product,id);
        
      
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
       
        Product updated_product = findProductById(id);
        updated_product.setProduct_name(product.getProduct_name());
        updated_product.setCategory(product.getCategory());
        updated_product.setSerial_code(product.getSerial_code());
        updated_product.setTotal_quantities(product.getTotal_quantities());
        return productRepository.save(updated_product);
    }

    @Override
    public void deleteProductbyId(Integer id) {
        
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProducts() {
        
        return productRepository.findAll();
    }

    // static Product DoesProductExist(Optional<Product> product , Integer id){

    //     if(product.isPresent()){
    //         return product.get();
    //     }
    //     else{
    //         throw new CustomerNotFoundException(id);
    //     }
    // }
    
}
