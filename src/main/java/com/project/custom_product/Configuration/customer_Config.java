package com.project.custom_product.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.project.custom_product.Service.Service_Impl.serviceImpl;

@Configuration
public class customer_Config {

    @Bean 
    public serviceImpl serviceBean(){
        
        return new serviceImpl();
            
        
    }


    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }

    
}
