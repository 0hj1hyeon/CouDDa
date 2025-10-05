package com.example.CouDDa.repository;

import com.example.CouDDa.domain.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingInfoRepository extends JpaRepository<ShippingInfo, Long> {

    // ⭐ 추가된 부분: Order 엔티티의 ID(orderId)를 기준으로 ShippingInfo를 조회합니다.
    // Spring Data JPA는 'findBy[연관관계 필드명]_[필드ID명]' 패턴을 인식합니다.
    // ShippingInfo 엔티티의 필드 이름이 'order'이고, Order 엔티티의 ID가 'id'이므로,
    // findByOrder_Id(Long orderId)로 작성하면 자동으로 쿼리가 생성됩니다.
    Optional<ShippingInfo> findByOrder_Id(Long orderId);

    // 운송장 번호로 배송 정보를 찾는 메서드 (기존)
    Optional<ShippingInfo> findByTrackingNumber(String trackingNumber);
}