package com.project.custom_product.Repository.repository;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Respository.purchase_repository;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;
import com.project.custom_product.entities.Purchase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
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

        customer1 = Customer.builder()
            .first_name("Ryan")
            .last_name("Henry")
            .address("Hans Holmboes gate 9 ")
            .telefon_number("00000000")
            .build();
            
        customer2 = Customer.builder()
            .first_name("Ahmed")
            .last_name("Ali")
            .address("Sognsveien 102 T ")
            .telefon_number("00000001")
            .build();
        
        custom_repos.save(customer1);
        custom_repos.save(customer2);

        product1 = Product.builder()
        .product_name("Beef burger")
        .category("fastfood")
        .total_quantities(10)
                .serial_code("74631f104232aqx")
        .build();

        product2 = Product.builder()
        .product_name("Pepsi")
        .category("softdrinks")
        .total_quantities(20)
                .serial_code("74632f804232aqx")
        .build();

        product_repos.save(product1);
        product_repos.save(product2);
      

        purchase1 = Purchase.builder()
        .purchase_code("3245907BQ")
        .price(45)
        .total_quantities(1)
        .customer(customer1)
        .product(product1)
        .build();

        
        purchase2 = Purchase.builder()
        .purchase_code("3245010CQ")
        .price(35)
        .total_quantities(3)
        .customer(customer1)
        .product(product2)
        .build();

        
        purchase3 = Purchase.builder()
        .purchase_code("3114508CE")
        .price(40)
        .total_quantities(2)
        .customer(customer2)
        .product(product1)
        .build();


        purchase_repos.save(purchase1);
        purchase_repos.save(purchase2);
        purchase_repos.save(purchase3);

    }

    @AfterEach

    void teardown(){

       
        purchase_repos.deleteAll();
        product_repos.deleteAll();
        custom_repos.deleteAll();

    }
    
    @Test
    void save_All_purchases(){
        Purchase purchase_ny = purchase_repos.save(purchase1);
        Assertions.assertThat(purchase_ny).isNotNull();
        Assertions.assertThat(purchase_ny).isEqualTo(purchase1);
    }

    @Test
    void check_findByCustomerIdAndProductId(){

        Optional<Purchase> purch =  purchase_repos.findByCustomerIdAndProductId(customer1.getId(),product1.getId());
      
        assertEquals(purch.get(), purchase1);
        assertNotEquals(purch.get(), purchase2);
               
    }


    @Test
    void check_findPurchasesByCustomerId(){

        List<Purchase> purchases = purchase_repos.findByCustomerId(customer1.getId());
        assertArrayEquals(purchases.stream().toArray(Purchase[]::new), List.of(purchase1,purchase2).stream().toArray(Purchase[]::new));


    }

    @Test
    void findPurchasesByProductIdTest(){
       
          List<Purchase> purchases = purchase_repos.findByProductId(product1.getId());
          assertArrayEquals(purchases.stream().toArray(Purchase[]::new), List.of(purchase1,purchase3).stream().toArray(Purchase[]::new));    


    }

    @Test
     void get_All_purchases(){

        Iterable<Purchase> purchases = purchase_repos.findAll();
        Assertions.assertThat(purchases).isNotNull();

        Assertions.assertThat(purchases).size().isEqualTo(3);

    }

    @Test

    void check_deletePurchaseByCustomerIdAndProductId(){

       Optional<Purchase> purchase = purchase_repos.findByCustomerIdAndProductId(customer1.getId(),product1.getId());

       assertTrue(purchase.isPresent()); // making sure that the object exists
            
       purchase_repos.deletePurchaseByCustomerIdAndProductId(customer1.getId(),product1.getId());
       
       
       Optional<Purchase> purchase2 = purchase_repos.findByCustomerIdAndProductId(customer1.getId(),product1.getId());

       assertTrue(!purchase2.isPresent()); // verifiying that the deleted entity no longer exists. 

       

       

       

       
              
    
    }   
}
