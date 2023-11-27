package com.project.custom_product.Service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.project.custom_product.exception.NotFoundException;

@Component
public class DoesExist<T> { // checking for an object if it exists

    public  T DoesObjectExist(Optional<T> object, Integer id){

        if(object.isPresent()){
            return object.get();
        }
        else{
            throw new NotFoundException(id);
        }
    }

}