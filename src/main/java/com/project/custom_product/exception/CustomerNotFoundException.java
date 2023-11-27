package com.project.custom_product.exception;

public class CustomerNotFoundException extends RuntimeException {


    public CustomerNotFoundException(Integer id){

        super("The customer with id: " + id + ""  + " does not exist in our records");
    }

    
}
