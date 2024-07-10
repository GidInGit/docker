package com.example.demo.Controllers;

import com.example.demo.Entity.Payloads.UpdateProductPayload;
import com.example.demo.Entity.Product;
import com.example.demo.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products-api/product/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return productService.findProduct(productId).orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @GetMapping()
    Product getProduct(@ModelAttribute("product") Product product) {
        return product;
    }

    @PatchMapping()
    ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,
                                    @Valid @RequestBody UpdateProductPayload productPayload,
                                    BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        productService.updateProduct(productId, productPayload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException e, Locale locale) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, messageSource.getMessage(e.getMessage(), new Object[0], locale)));
    }
}
