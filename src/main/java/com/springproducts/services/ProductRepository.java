package com.springproducts.services;

import java.util.List;

import com.springproducts.model.Product;

public interface ProductRepository {
    List<Product> findAllProducts();
    Product findProductById(int id);
    void saveProduct(Product product);
    void deleteProductById(int id);
    void updateQuantityProduct(Product product);
}
