package com.example.CouDDa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipping_info")
@Getter
@Setter
@NoArgsConstructor
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 배송 정보 고유 식별자

    // ⭐ order_id (FK): Order 엔티티와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 배송과 연결된 주문 (Foreign Key)

    @Column(name = "receiver_name", nullable = false, length = 100)
    private String receiverName; // 수령인 이름

    @Column(nullable = false, length = 255)
    private String address; // 배송 주소

    @Column(name = "tracking_number", unique = true, length = 255)
    private String trackingNumber; // 운송장 번호

    @Column(nullable = false, length = 50)
    private String status; // 배송 상태 (PREPARING, SHIPPED, DELIVERED)

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 상태 업데이트 시간

    // 객체가 생성되거나 수정될 때 시간을 자동으로 기록합니다.
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}