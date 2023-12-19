package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.entities.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Customer_serviceImplTest {

    @Mock
    private customer_repository repos;

    @InjectMocks
    private Customer_serviceImpl customer_service;

    @Test
    void saveCustomer() {

        Customer customer1 = Customer.builder()
                .id(1)
                .first_name("Ryan")
                .last_name("Henry")
                .address("Hans Holmboes gate 9 ")
                .telefon_number("00000000")
                .build();

        when(repos.save(Mockito.any(Customer.class))).thenReturn(customer1);
        Customer saved = customer_service.saveCustomer(customer1);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(customer1.getId()).isEqualTo(1);

    }

    @Test
    void deleteCustomerById() {
        int customer_id = 1;
        Customer customer1 = Customer.builder()
                .first_name("Ryan")
                .last_name("Henry")
                .address("Hans Holmboes gate 9 ")
                .telefon_number("00000000")
                .build();

        when(repos.findById(customer_id)).thenReturn(Optional
                .ofNullable(customer1));

        doNothing().when(repos).delete(customer1);

        assertAll(() -> customer_service.deleteCustomerById(customer_id));

        
    }

    @Test
    void updateCustomer() {

        int customer_id = 1;
        Customer customer = Customer.builder()
                .first_name("Ahmed")
                .last_name("Ali")
                .address("Sognsveien 102 T ")
                .telefon_number("00000001")
                .build();


        when(repos.findById(customer_id)).thenReturn(Optional
                .ofNullable(customer));

        when(repos.save(customer)).thenReturn(customer);

        Customer updated = customer_service.updateCustomer(customer_id,customer);
        Assertions.assertThat(updated).isNotNull();




    }

    @Test
    void findCustomerById() {

        int customer_id = 1;
        Customer customer = Customer.builder()
                .first_name("Ahmed")
                .last_name("Ali")
                .address("Sognsveien 102 T ")
                .telefon_number("00000001")
                .build();

        when(repos.findById(customer_id))
                .thenReturn(Optional.ofNullable(customer));

        Customer check_customer = customer_service.findCustomerById(customer_id);

        Assertions.assertThat(check_customer).isNotNull();
    }

    @Test
    void getAllcustomers() {
        
        Customer customer = Customer.builder()
                .first_name("Ahmed")
                .last_name("Ali")
                .address("Sognsveien 102 T ")
                .telefon_number("00000001")
                .build();

        List<Customer>customers = List.of(customer);

         when(repos.findAll()).thenReturn(customers);

        List<Customer> return_customers = customer_service.getAllcustomers();
        Assertions.assertThat(return_customers).size().isGreaterThan(0);




    }
}