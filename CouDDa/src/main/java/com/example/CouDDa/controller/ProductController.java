package com.example.CouDDa.controller;

import com.example.CouDDa.domain.Product;
import com.example.CouDDa.dto.ProductResponseDto;
import com.example.CouDDa.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product productDetails,
                                                 @RequestParam Long sellerId,
                                                 @RequestParam Long categoryId) {
        Product newProduct = productService.createProduct(productDetails, sellerId, categoryId);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}