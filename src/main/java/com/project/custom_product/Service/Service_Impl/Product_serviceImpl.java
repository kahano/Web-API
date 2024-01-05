package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Service.Product_service;
import com.project.custom_product.entities.Product;
import com.project.custom_product.exception.ProductNotFoundException;

import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class Product_serviceImpl implements Product_service {


    private final product_repository productRepository ;



    @Override
    public Product saveProduct(Product product) {
        
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {

        return productRepository.findById(id).
                orElseThrow(()-> new ProductNotFoundException(id));
        
        
      
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
        
        return (List<Product>) productRepository.findAll();
    }

   

    
}
