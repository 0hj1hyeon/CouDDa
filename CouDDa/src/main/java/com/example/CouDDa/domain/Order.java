package com.example.CouDDa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 고유 식별자 (PK) (주문 번호)

    // user_id (FK): User 엔티티와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 주문을 한 회원 (Foreign Key)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0")
    private Integer  totalAmount; // 총 주문 금액

    @Column(nullable = false, length = 50)
    private String status; // 주문 상태 (PENDING, PAID 등)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 주문 일시


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();     // 이 주문에 속한 상품 목록

    // 현재 시간 (주문 일시)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}