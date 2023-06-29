package com.springproducts.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springproducts.model.Product;
import com.springproducts.services.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
        public String home() {
        return "Hola Mundo";
    }

    @GetMapping("/getproducts")
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @PostMapping("/test")
    public void addTestProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(i);
            product.setProductName("Product-Example: " + i);
            product.setProductDescription("Description Example: " + i);
            product.setProductPrice(new Random().nextInt(500) + 1);
            product.setProductQuantity(new Random().nextInt(10) + 1);
            products.add(product);
        }
        for (Product product : products) {
            productRepository.saveProduct(product);
        }
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productRepository.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable int id) {
        productRepository.updateQuantityProduct(productRepository.findProductById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable int id) {
        productRepository.deleteProductById(id);
    }

}