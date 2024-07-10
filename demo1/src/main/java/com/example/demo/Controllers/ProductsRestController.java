package com.example.demo.Controllers;

import com.example.demo.Entity.Payloads.CreateProductPayload;
import com.example.demo.Entity.Product;
import com.example.demo.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products-api/products")
@RequiredArgsConstructor
public class ProductsRestController {
    private final ProductService productService;

    @GetMapping()
    Iterable<Product> getProducts() {
        return productService.findProducts();
    }
    @PostMapping()
    ResponseEntity<?> addProduct(@Valid @RequestBody CreateProductPayload productPayload, BindingResult bindingResult)
            throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }else {
            Product product = productService.saveProduct(productPayload.name(), productPayload.count(), productPayload.price());
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
    }
}
