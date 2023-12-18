package com.project.custom_product.Repository.repository;

import com.project.custom_product.Respository.product_repository;
import com.project.custom_product.entities.Product;
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

@DataJpaTest(  properties = {
    "spring.jpa.properties.javax.persistence.validation.mode=none"
})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class product_repositoryTest {

    @Autowired
    private product_repository product_repos; 
    
    Product product1;
    Product product2;



    @BeforeEach
    void setUp() {

        product1 = Product.builder()
        .product_name("Beef burger")
        .category("fastfood")
        .total_quantities(10)
        .build();

        product2 = Product.builder()
        .product_name("Pepsi")
        .category("softdrinks")
        .total_quantities(20)
        .build();

        product_repos.save(product1);
        product_repos.save(product2);
        
            
    }

    @AfterEach
    void tearDown() {

        product_repos.deleteAll();
    }

    @Test
    public void save_All_products(){

        Product saved = product_repos.save(product1);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
        
    }

    @Test
    public void get_All_products(){

        List<Product> products = product_repos.findAll();
        Assertions.assertThat(products).isNotNull();
        Assertions.assertThat(products).size().isEqualTo(2);


    }

    @Test

    public void check_findProductById(){

        Product product = product_repos.findById(product1.getId()).get();

        Assertions.assertThat(product).isNotNull();

    }

    @Test 

    public void check_findProductByProduct_Name(){

        Product product = product_repos.existsByProduct_Name(product2.getProduct_name()).get();
        Assertions.assertThat(product).isNotNull();
    }


    @Test 

    public void check_updateProduct(){

        Product product = product_repos.findById(product1.getId()).get();

        product.setProduct_name("Chilli cheese burger");
        product.setCategory("fastfood");
        product.setTotal_quantities(8);

        Product updated = product_repos.save(product);

        Assertions.assertThat(updated.getProduct_name()).isNotNull();
        Assertions.assertThat(updated.getTotal_quantities()).isNotNull();
        Assertions.assertThat(updated.getCategory()).isNotNull();
    }

    @Test 

    public void check_deleteProduct(){
        
        Product product = product_repos.findById(product1.getId()).get();
        Assertions.assertThat(product).isNotNull();

        product_repos.deleteById(product.getId());

        Optional<Product> check_deleteproduct = product_repos.findById(product1.getId());
        
        Assertions.assertThat(!check_deleteproduct.isPresent()); // verifies that entity is deleted

         Assertions.assertThat(check_deleteproduct).isEmpty(); // another alternative to check the entity
    }


}