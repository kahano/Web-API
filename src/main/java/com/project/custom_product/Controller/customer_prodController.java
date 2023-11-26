package com.project.custom_product.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.custom_product.DTO.customer_ProductDTO;
import com.project.custom_product.Service.Service_Impl.serviceImpl;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.exception.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/customers")
public class customer_prodController {


    @Autowired
    private serviceImpl custom_service;

    @Autowired
    private ModelMapper mapper;


    private customer_ProductDTO convertEntityToDto(Customer customer){
        
        
         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        customer_ProductDTO customerDTO = mapper.map(customer,customer_ProductDTO.class);
        return customerDTO;
    }


   



  
    @PostMapping
    public ResponseEntity<customer_ProductDTO> create (@RequestBody customer_ProductDTO customerDTO){

        
        Customer customer = mapper.map(customerDTO,Customer.class);

        custom_service.saveCustomer(customer);

   

        return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{customer_id}")

    public ResponseEntity<customer_ProductDTO> getCustommer(@PathVariable Integer customer_id){


        Customer customer = custom_service.findCustomerById(customer_id);
        customer_ProductDTO customerDTO = mapper.map(customer,customer_ProductDTO.class);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        

    }

    
    @GetMapping

    public ResponseEntity<List<customer_ProductDTO>> findAllCustomers(){
        
        List<Customer> customers = custom_service.getAllcustomers();
       
        return new ResponseEntity<> (customers.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PutMapping("/{customer_id}")
    public ResponseEntity<customer_ProductDTO> update(@PathVariable Integer customer_id, @RequestBody customer_ProductDTO customer_dto){


          Customer customer = mapper.map(customer_dto,Customer.class);
       
         custom_service.updateCustomer(customer_id, customer);
        
        customer_ProductDTO customerDTO = convertEntityToDto(customer);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);

  

        
    }

    @DeleteMapping("/{customer_id}")
    public ResponseEntity<?> delete_customer(@PathVariable Integer customer_id){
        custom_service.deleteCustomerById(customer_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }


    

    
    
}
