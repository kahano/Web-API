package com.project.custom_product.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer customer_id){

        super("The customer with id: " + customer_id + ""  + " does not exist in our records");
    }


    
}
