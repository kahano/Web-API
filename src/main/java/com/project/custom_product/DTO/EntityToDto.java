package com.project.custom_product.DTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;



public class EntityToDto<K>{

    @Autowired
    private ModelMapper mapper;
    
    

   

    public Object entity_toDto(K objekt){
        
        
         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);


        Object obj = mapper.map(objekt,Object.class);
        return obj;
    }
}

