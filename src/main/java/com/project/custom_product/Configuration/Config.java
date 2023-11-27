package com.project.custom_product.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.custom_product.Service.DoesExist;
import com.project.custom_product.Service.Service_Impl.Customer_serviceImpl;
import com.project.custom_product.Service.Service_Impl.Product_serviceImpl;

@Configuration
public class Config {

    @Bean 
    public Customer_serviceImpl serviceBean(){
        
        return new Customer_serviceImpl();
            
        
    }

    // @Bean

    // public Product_serviceImpl serviceBean(){
        
    //     return new Product_serviceImpl();
    // }


    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }

   

    
}
