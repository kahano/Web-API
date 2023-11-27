package com.project.custom_product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int id){

        super("The product with " + id + " does not exist in our records");
    }
    
}
