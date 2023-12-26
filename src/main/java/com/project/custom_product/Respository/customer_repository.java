package com.project.custom_product.Respository;

import com.project.custom_product.entities.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;





public interface customer_repository extends JpaRepository<Customer,Integer> {

     @Query(
            value = "select * " +
            "from customer where telefon_number = :telefon_number",
            nativeQuery = true
    )
    Optional<Customer> findByTelefon_Number(@Param("telefon_number") String telefon_number);
    
}
