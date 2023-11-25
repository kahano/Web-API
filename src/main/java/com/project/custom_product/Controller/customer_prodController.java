package com.project.custom_product.Controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.Service.Service_Impl.serviceImpl;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.mapper.customer_productMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/customers")
public class customer_prodController {


    @Autowired
    private serviceImpl custom_service;

    @Autowired
    private ModelMapper mapper;


  
    @PostMapping
    public ResponseEntity<customer_ProductDTO> create (@RequestBody customer_ProductDTO customerDTO){

         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        
        Customer customer = mapper.map(customerDTO,Customer.class);

        custom_service.saveCustomer(customer);

   

        return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{customer_id}")

    public ResponseEntity<customer_ProductDTO> findById(@PathVariable Integer customer_id){

         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        Optional<Customer> customer = custom_service.findCustomerById(customer_id);
        customer_ProductDTO customerDTO = mapper.map(customer,customer_ProductDTO.class);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        

    }

    

    
    
}
