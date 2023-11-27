package com.project.custom_product.Service;

import java.util.List;

import com.project.custom_product.entities.Product;

public interface Product_service {

    Product saveProduct(Product product);

    Product findProductById(Integer id);

    Product updateProduct(Integer id, Product product);

    void deleteProductbyId(Integer id);

    List<Product> getProducts();
    
}
