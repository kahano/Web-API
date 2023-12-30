package com.project.custom_product.exception;

public class AppUserNotFoundException extends RuntimeException {

    
    public AppUserNotFoundException(String name){

        super("The user: " + name + ""  + " does not exist in our database");
    }
    
}
