package com.project.custom_product.exception;

public class PurchaseNotFoundExcpetion extends RuntimeException{

    public PurchaseNotFoundExcpetion(int id1, int id2){

        super("The customer with id: " + id1 + " and the product with id: " + id2 + " don't exists in our records" );
    }
    
}
