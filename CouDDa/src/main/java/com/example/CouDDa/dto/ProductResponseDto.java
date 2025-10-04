package com.example.CouDDa.dto;

import com.example.CouDDa.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private int stockQuantity;
    private String sellerName; // 판매자 이름
    private String categoryName; // 카테고리 이름

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();

        if (product.getSeller() != null) {
            this.sellerName = product.getSeller().getName();
        }
        if (product.getCategory() != null) {
            this.categoryName = product.getCategory().getName();
        }
    }
}