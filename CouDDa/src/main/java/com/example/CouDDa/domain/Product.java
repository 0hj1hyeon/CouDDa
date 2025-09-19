package com.example.CouDDa.domain;

import java.time.LocalDateTime;

public class Product {
    private long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private long sellerId;
    private long categoryId;
    private LocalDateTime createdAt;

    public Product(long id, String name, String description, double price, int stockQuantity, long sellerId, long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();
    }

    // 상품 정보가 수정되더라도, 명세에 따라 updatedAt 필드가 없으므로 별도의 갱신 로직은 추가하지 않습니다.

    // getters, setters, toString() 등
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
