package com.springproducts.repository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springproducts.model.Product;
import com.springproducts.services.ProductRepository;

@Repository
public class ProductJsonRepository implements ProductRepository {

    private final ObjectMapper objectMapper;
    private final String filePath = "products.json";

    public ProductJsonRepository() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return products;
        }
        try {
            products = objectMapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findProductById(int id) {
        List<Product> products = findAllProducts();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void saveProduct(Product product) {
        List<Product> products = findAllProducts();
        products.add(product);
        saveAllProducts(products);
    }

    @Override
    public void deleteProductById(int id) {
        List<Product> products = findAllProducts();
        products.removeIf(product -> product.getId() == id);
        saveAllProducts(products);
    }

    @Override
    public void updateQuantityProduct(Product product) {
        List<Product> products = findAllProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.get(i).setProductQuantity(product.getProductQuantity()-1);
                saveAllProducts(products);
                break;
            }
        }
    }

    private void saveAllProducts(List<Product> products) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            objectMapper.writeValue(file, products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
