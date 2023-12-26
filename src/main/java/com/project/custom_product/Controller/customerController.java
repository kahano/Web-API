package com.project.custom_product.Controller;

import com.project.custom_product.DTO.customerDTO;
import com.project.custom_product.MapperDTO.CustomerMapper;
import com.project.custom_product.Service.Service_Impl.Customer_serviceImpl;
import com.project.custom_product.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/customers")
public class customerController {


    @Autowired
    private Customer_serviceImpl custom_service;

   

    @Autowired 
    private CustomerMapper mapper;

   
  
    @PostMapping
    @PreAuthorize(value = "hasAuthority('customer:write')")
    public ResponseEntity<customerDTO> create (@RequestBody customerDTO customerDTO){

        
        Customer customer = mapper.convertCustomerDTO_TOCustomer(customerDTO);

        custom_service.saveCustomer(customer);

   

        return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('customer:read')")
    public ResponseEntity<customerDTO> getCustommer(@PathVariable Integer id){


        Customer customer = custom_service.findCustomerById(id);
        customerDTO customerDTO = mapper.converCustomerToDto(customer);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        

    }

    
    @GetMapping
    @PreAuthorize(value = "hasAuthority('customer:read')")
    public ResponseEntity<List<customerDTO>> findAllCustomers(){
        
        List<Customer> customers = custom_service.getAllcustomers();

       
        return new ResponseEntity<> (customers.stream()
                .map(mapper::converCustomerToDto )
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('customer:write')")
    public ResponseEntity<customerDTO> update(@PathVariable Integer id, @RequestBody customerDTO customer_dto){


          Customer customer = mapper.convertCustomerDTO_TOCustomer(customer_dto);
       
         custom_service.updateCustomer(id, customer);

        
        customerDTO customerDTO = mapper.converCustomerToDto(customer);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);

  

        
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('customer:write')")
    public ResponseEntity<?> delete_customer(@PathVariable Integer id){
        custom_service.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }


    

    
    
}
