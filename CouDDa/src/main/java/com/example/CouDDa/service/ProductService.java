package com.example.CouDDa.service;

import com.example.CouDDa.domain.Category;
import com.example.CouDDa.domain.Product;
import com.example.CouDDa.domain.Seller;
import com.example.CouDDa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerService sellerService; // SellerService를 주입
    private final CategoryService categoryService; // CategoryService를 주입

    @Autowired
    public ProductService(ProductRepository productRepository, SellerService sellerService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.categoryService = categoryService;
    }

    /**
     * 새로운 상품을 등록합니다.
     * @param productDetails 상품 정보 객체
     * @param sellerId 상품을 등록할 판매자 ID
     * @param categoryId 상품이 속할 카테고리 ID
     * @return 저장된 상품 객체
     */
    public Product createProduct(Product productDetails, Long sellerId, Long categoryId) {
        // 1. SellerService를 통해 판매자 유효성 검사 및 객체 가져오기
        Seller seller = sellerService.getSellerById(sellerId);

        // 2. CategoryService를 통해 카테고리 유효성 검사 및 객체 가져오기
        Category category = categoryService.getCategoryById(categoryId);

        // 3. 상품 객체에 판매자와 카테고리 객체 연결
        productDetails.setSeller(seller);
        productDetails.setCategory(category);
        productDetails.setCreatedAt(LocalDateTime.now());

        // 4. 상품 저장
        return productRepository.save(productDetails);
    }

    /**
     * 모든 상품 목록을 조회합니다.
     * @return 상품 목록
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
