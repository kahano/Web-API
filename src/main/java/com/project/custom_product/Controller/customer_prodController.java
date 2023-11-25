package com.project.custom_product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
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


    private customer_productMapper mapper;

  
    @PostMapping
    public ResponseEntity<customer_ProductDTO> create (@RequestBody customer_ProductDTO customer_productDTO){


        Customer customer = mapper.toCustomer(customer_productDTO);

        custom_service.saveCustomer(customer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    

    
    
}
