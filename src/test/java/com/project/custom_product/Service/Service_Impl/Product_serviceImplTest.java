package com.project.custom_product.Service.Service_Impl;

import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.entities.Product;
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
class Product_serviceImplTest {

    @Mock
    private product_repository repos;

    @InjectMocks
    private Product_serviceImpl product_service;
    @Test
    void saveProduct() {

        Product product1 = Product.builder()
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(45)
                .serial_code("74631f104232aqx")
                .build();

        when(repos.save(Mockito.any(Product.class))).thenReturn(product1);
        Product saved = repos.save(product1);
        Assertions.assertThat(saved).isNotNull();
    }

    @Test
    void findProductById() {
        int product_id = 1;
        Product product1 = Product.builder()
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(45)
                .serial_code("74631f104232aqx")
                .build();
        when(repos.findById(product_id))
                .thenReturn(Optional.ofNullable(product1));

        Product Is_productExists = product_service.findProductById(product_id);
        Assertions.assertThat(Is_productExists).isNotNull();
    }

    @Test
    void updateProduct() {

        int product_id = 1;
        Product product1 = Product.builder()
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(45)
                .serial_code("74631f104232aqx")
                .build();
        when(repos.findById(product_id))
                .thenReturn(Optional.ofNullable(product1));

        when(repos.save(product1)).thenReturn(product1);

        Product updated_product =  product_service.updateProduct(product_id,product1);
        Assertions.assertThat(updated_product).isNotNull();
    }

    @Test
    void deleteProductbyId() {

        int product_id = 1;
        Product product1 = Product.builder()
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(45)
                .serial_code("74631f104232aqx")
                .build();
        when(repos.findById(product_id))
                .thenReturn(Optional.ofNullable(product1));
        doNothing().when(repos).delete(product1);
        assertAll(() -> product_service.deleteProductbyId(product_id));


    }

    @Test
    void getProducts() {

        Product product1 = Product.builder()
                .product_name("Beef burger")
                .category("fastfood")
                .total_quantities(45)
                .serial_code("74631f104232aqx")
                .build();
        List<Product> products = List.of(product1);
        when(repos.findAll()).thenReturn(products);

        List<Product> get_products = product_service.getProducts();
        Assertions.assertThat(products).size().isGreaterThan(0);
    }
}