package com.example.CouDDa.service;

import com.example.CouDDa.domain.Category;
import com.example.CouDDa.domain.Product;
import com.example.CouDDa.domain.Seller;
import com.example.CouDDa.dto.ProductResponseDto;
import com.example.CouDDa.repository.ProductRepository;
import com.example.CouDDa.repository.SellerRepository;
import com.example.CouDDa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Product createProduct(Product productDetails, Long sellerId, Long categoryId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("판매자를 찾을 수 없습니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        productDetails.setSeller(seller);
        productDetails.setCategory(category);
        productDetails.setCreatedAt(LocalDateTime.now());

        return productRepository.save(productDetails);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
}