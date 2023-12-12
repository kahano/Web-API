package com.project.custom_product.Customer_Product_Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Respository.purchase_repository;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;
import com.project.custom_product.entities.Purchase;


@DataJpaTest
public class purchase_repositoryTest {

    @Autowired
    private purchase_repository purchase_repos;

    @Autowired

    private customer_repository custom_repos;

    @Autowired

    private product_repository product_repos;

    Customer customer1;
    Customer customer2;

    Product product1;
    Product product2;

    Purchase purchase1;
    Purchase purchase2;
    Purchase purchase3;


    @BeforeEach
    void setup(){

        customer1 = new Customer(1, "Henrik", "Larson","", "Sognsveien102T Oslo", "97351722", null );
        customer2 = new Customer(2, "Aleksandra", "Larson","", "Sognsveien102T Oslo", "97323722", null );
        custom_repos.save(customer1);
        custom_repos.save(customer2);

        product1 = new Product(1,"Beef burger", "fastfood", "487631208",6, null );
        product2 = new Product(2,"cheese burger", "fastfood", "487431218",4, null );
        product_repos.save(product1);
        product_repos.save(product2);


        purchase1 = new Purchase(1,"3245907BQ",45,4,0, custom_repos.findById(1).get(), product_repos.findById(1).get());
        purchase3 = new Purchase(2,"3245907BQ",40,2,0, custom_repos.findById(2).get(), product_repos.findById(1).get());
        purchase2 = new Purchase(2,"3245010CQ",35,4,0, custom_repos.findById(1).get(), product_repos.findById(2).get());


        purchase_repos.save(purchase1);
        purchase_repos.save(purchase2);
        purchase_repos.save(purchase3);

    }

    @AfterEach

    void teardown(){

        purchase1 = null;
        purchase2 = null;
        purchase3 = null;

        purchase_repos.deleteAll();

    }
    
    @Test
    void check_findByCustomerIdAndProductId(){

       Optional<Purchase>  purch =  purchase_repos.findByCustomerIdAndProductId(1,1);
       Optional<Purchase>  purch2 =  purchase_repos.findByCustomerIdAndProductId(2,1);
       
       assertEquals(purch.get(), purchase1);
       assertNotEquals(purch.get(), purchase2);
     //  assertNotEquals(purch2.get(), purchase1);

       assertEquals(purch.get().getPrice(), purchase1.getPrice());


    }

    @Test
    void check_findPurchasesByCustomerId(){

        List<Purchase> purchases = purchase_repos.findByCustomerId(1);
        assertArrayEquals(purchases.stream().toArray(Purchase[]::new), List.of(purchase1,purchase2).stream().toArray(Purchase[]::new));


    }

    @Test
    void check_findPurchasesByProductId(){

        List<Purchase> purchases = purchase_repos.findByProductId(1);
         assertArrayEquals(purchases.stream().toArray(Purchase[]::new), List.of(purchase1,purchase3).stream().toArray(Purchase[]::new));

    }

    @Test

    void check_deletePurchaseByCustomerIdAndProductId(){

        assertTrue(purchase_repos.existsById(1));
        purchase_repos.deletePurchaseByCustomerIdAndProductId(1,1);
        assertFalse(purchase_repos.existsById(1));
    
    }   
}
