package com.example.CouDDa.domain;

import jakarta.persistence.*;
import com.example.CouDDa.domain.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 상품 고유 식별자 (PK)

    // order_id (FK): Order 엔티티와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 속한 주문 (Foreign Key)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 상품

    @Column(nullable = false)
    private Integer quantity; // 주문 수량

    @Column(name = "price_at_purchase", nullable = false, precision = 10, scale = 2)
    private Integer priceAtPurchase; // 구매 당시 상품 가격
}