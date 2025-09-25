package com.example.CouDDa.controller;

import com.example.CouDDa.domain.Product;
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

    /**
     * POST /api/products
     * 새로운 상품 등록
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product productDetails,
                                                 @RequestParam Long sellerId,
                                                 @RequestParam Long categoryId) {
        Product newProduct = productService.createProduct(productDetails, sellerId, categoryId);
        return ResponseEntity.ok(newProduct);
    }

    /**
     * GET /api/products
     * 모든 상품 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}