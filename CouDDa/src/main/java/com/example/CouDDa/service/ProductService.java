package com.example.CouDDa.service;

import com.example.CouDDa.domain.Product;
import com.example.CouDDa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // ⭐ SellerRepository와 CategoryRepository는 더 이상 필요 없습니다.
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 상품 등록 메서드 (최종 버전)
     * ID를 직접 설정합니다.
     */
    public Product createProduct(Product productDetails, Long sellerId, Long categoryId) {
        productDetails.setSellerId(sellerId);
        productDetails.setCategoryId(categoryId);
        productDetails.setCreatedAt(LocalDateTime.now());

        return productRepository.save(productDetails);
    }

    /**
     * 모든 상품 목록 조회 메서드 (변경 없음)
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}