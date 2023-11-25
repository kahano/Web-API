package com.project.custom_product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.entities.Customer;

@SpringBootApplication
public class CustomProductApplication implements CommandLineRunner{

	@Autowired
	private customer_repository repos ;


	public static void main(String[] args) {
		SpringApplication.run(CustomProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Customer customer = new Customer();

		customer.setFirst_name("Mohamed");
		customer.setLast_name("Ali");
		customer.setAddress("sognsveien 102 T, Oslo");
		customer.setTelefon_number("97351722");
		repos.save(customer);



	}

	

}
