package com.example.demo.Services;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Payloads.UpdateProductPayload;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findProduct(int id);
    Iterable<Product> findProducts();
    Product saveProduct(String name, int count, double price);
    void deleteProduct(int id);
    void updateProduct(int id, UpdateProductPayload payload);
}
