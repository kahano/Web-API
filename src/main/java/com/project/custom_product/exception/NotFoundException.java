package com.project.custom_product.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Integer id){

        super("The Object with id: " + id + ""  + " does not exist in our records");
    }

    
}
