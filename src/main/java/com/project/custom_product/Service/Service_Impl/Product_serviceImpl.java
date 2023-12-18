package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Service.Product_service;
import com.project.custom_product.entities.Product;
import com.project.custom_product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Product_serviceImpl implements Product_service {

    @Autowired
    private product_repository productRepository ;



    @Override
    public Product saveProduct(Product product) {
        
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {

        // Optional<Product> product = productRepository.findById(id);
        // return DoesObjectExist(product,id);

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

    // private Product DoesObjectExist(Optional<Product> object, Integer id){

    //     if(object.isPresent()){
    //         return object.get();
    //     }
    //     else{
    //         throw new ProductNotFoundException(id);
    //     }
    // }



    
}
