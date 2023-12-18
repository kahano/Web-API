package com.project.MapperDTO;

import org.springframework.stereotype.Component;

import com.project.custom_product.DTO.customerDTO;
import com.project.custom_product.entities.Customer;

@Component
public class CustomerMapper {

    public customerDTO converCustomerToDto(Customer customer){
        
        return customerDTO.builder()
        .first_name(customer.getFirst_name())
        .last_name(customer.getLast_name())
        .address(customer.getAddress())
        .telefon_number(customer.getTelefon_number())
        .build();
    }

    public Customer convertCustomerDTO_TOCustomer(customerDTO customer){
        
        return Customer.builder()
        .first_name(customer.getFirst_name())
        .last_name(customer.getLast_name())
        .address(customer.getAddress())
        .telefon_number(customer.getTelefon_number())
        .build();
    }
    
}
