package com.example.demo.Services.Impl;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Payloads.UpdateProductPayload;
import com.example.demo.Repositories.ProductRepository;
import com.example.demo.Services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public Optional<Product> findProduct(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Product> findProducts() {
        return repository.findAll();
    }

    @Override
    public Product saveProduct(String name, int count, double price) {
        return repository.save(new Product(null, name, count, price));
    }

    @Override
    public void deleteProduct(int id) {
        repository.deleteById(id);
    }

    @Override
    public void updateProduct(int id, UpdateProductPayload payload) {
        repository.findById(id).ifPresent(product -> {
            product.setName(payload.name());
            product.setCount(payload.count());
            product.setPrice(payload.price());
        });
    }
}
