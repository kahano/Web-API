package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.customer_repository;
import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.Respository.purchase_repository;
import com.project.custom_product.entities.Customer;
import com.project.custom_product.entities.Product;
import com.project.custom_product.entities.Purchase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Purchase_serviceImplTest {

    @Mock
    private purchase_repository purchase_repository;

    @Mock
    private customer_repository customer_repository;

    @Mock
    private product_repository product_repository;

    @Mock
    private Customer_serviceImpl customer_service;

    @Mock
    private Product_serviceImpl product_service;


    @InjectMocks
    private Purchase_serviceImpl purchase_service;

     Customer customer1;
     Product product1;
    private Purchase purchase;




    @BeforeEach
    void init() {
        customer1 = Customer.builder()
                .id(1)
                .first_name("Ryan")
                .last_name("Henry")
                .address("Hans Holmboes gate 9 ")
                .telefon_number("00000000")
                .build();



        product1 = Product.builder()
                .id(1)
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(10)
                .serial_code("74631f104232aqx")
                .build();

        customer_repository.save(customer1);
        product_repository.save(product1);



        purchase = Purchase.builder()
                .purchase_code("3245907BQ")
                .price(45)
                .total_quantities(1)
                .customer(customer1)
                .product(product1)
                .build();


    }


    @Test
    void savePurchase() {
         int customer_id = 1;
         int product_id = 1;

        when(purchase_repository.save(Mockito.any(Purchase.class))).thenReturn(purchase);

        Purchase saved = purchase_service.savePurchase(purchase, customer1.getId(), product1.getId());
        Assertions.assertThat(customer1.getId()).isEqualTo(customer_id);//
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved).isSameAs(purchase);


    }

    @Test
    void findPurchaseBYId() {
    }

    @Test
    void updatePurchase() {
    }

    @Test
    void delete() {
    }

    @Test
    void getTotalPricePurchase_per_Customer() {
    }

    @Test
    void getAllCustomersPurchased() {
    }

    @Test
    void getAllProductssPurchased() {
    }

    @Test
    void getAllPurchases() {
    }



    @Test
    void testSavePurchase() {
    }

    @Test
    void testFindPurchaseBYId() {
    }

    @Test
    void testUpdatePurchase() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testGetTotalPricePurchase_per_Customer() {
    }

    @Test
    void testGetAllCustomersPurchased() {
    }

    @Test
    void testGetAllProductssPurchased() {
    }

    @Test
    void testGetAllPurchases() {
    }
}