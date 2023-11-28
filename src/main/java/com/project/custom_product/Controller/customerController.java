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
import com.project.custom_product.DTO.customerDTO;
import com.project.custom_product.Service.Service_Impl.Customer_serviceImpl;
import com.project.custom_product.entities.Customer;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/customers")
public class customerController {


    @Autowired
    private Customer_serviceImpl custom_service;

    @Autowired
    private ModelMapper mapper;

    //private EntityToDto<Customer> DTO;


    private customerDTO convertEntityToDto(Customer customer){
        
        
         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        customerDTO customerDTO = mapper.map(customer,customerDTO.class);
        return customerDTO;
    }


   



  
    @PostMapping
    public ResponseEntity<customerDTO> create (@RequestBody customerDTO customerDTO){

        
        Customer customer = mapper.map(customerDTO,Customer.class);

        custom_service.saveCustomer(customer);

   

        return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    public ResponseEntity<customerDTO> getCustommer(@PathVariable Integer id){


        Customer customer = custom_service.findCustomerById(id);
        customerDTO customerDTO = mapper.map(customer,customerDTO.class);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        

    }

    
    @GetMapping

    public ResponseEntity<List<customerDTO>> findAllCustomers(){
        
        List<Customer> customers = custom_service.getAllcustomers();

       
        return new ResponseEntity<> (customers.stream()
                .map(this::convertEntityToDto )
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<customerDTO> update(@PathVariable Integer id, @RequestBody customerDTO customer_dto){


          Customer customer = mapper.map(customer_dto,Customer.class);
       
         custom_service.updateCustomer(id, customer);

        
        customerDTO customerDTO = convertEntityToDto(customer);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);

  

        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete_customer(@PathVariable Integer id){
        custom_service.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }


    

    
    
}
