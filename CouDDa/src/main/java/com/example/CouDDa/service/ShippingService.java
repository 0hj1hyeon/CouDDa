package com.example.CouDDa.service;

import com.example.CouDDa.domain.Order;
import com.example.CouDDa.domain.ShippingInfo;
import com.example.CouDDa.dto.ShippingRequestDto;
import com.example.CouDDa.dto.ShippingResponseDto;
import com.example.CouDDa.repository.ShippingInfoRepository;
import com.example.CouDDa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ShippingService {

    private final ShippingInfoRepository shippingInfoRepository;
    private final OrderRepository orderRepository; // Order 엔티티를 찾기 위해 필요

    public ShippingService(ShippingInfoRepository shippingInfoRepository, OrderRepository orderRepository) {
        this.shippingInfoRepository = shippingInfoRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * 새로운 배송 정보 생성 및 등록
     */
    @Transactional
    public ShippingResponseDto createShippingInfo(ShippingRequestDto requestDto) {
        // 1. 주문 ID로 Order 엔티티 조회 (유효성 검사)
        Order order = orderRepository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다. ID: " + requestDto.getOrderId()));

        // 2. ShippingInfo 엔티티 생성 및 데이터 매핑
        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.setOrder(order);
        shippingInfo.setReceiverName(requestDto.getReceiverName());
        shippingInfo.setAddress(requestDto.getAddress());

        // 3. 운송장 번호 자동 생성 (예시)
        shippingInfo.setTrackingNumber(UUID.randomUUID().toString());

        // 4. 초기 상태 설정
        shippingInfo.setStatus("PREPARING");

        // 5. 저장 및 DTO 변환 후 반환
        ShippingInfo savedInfo = shippingInfoRepository.save(shippingInfo);
        return new ShippingResponseDto(savedInfo);
    }

    /**
     * 특정 주문의 배송 정보 조회
     */
    public ShippingResponseDto getShippingInfoByOrderId(Long orderId) {
        // ⭐ 수정된 부분: findByOrder_Id 로직을 사용합니다.
        ShippingInfo info = shippingInfoRepository.findByOrder_Id(orderId)
                .orElseThrow(() -> new RuntimeException("해당 주문의 배송 정보를 찾을 수 없습니다."));
        return new ShippingResponseDto(info);
    }

    @Transactional
    public ShippingResponseDto updateShippingStatus(String trackingNumber, String newStatus) {
        // ⭐ 수정된 부분: findByTrackingNumber()가 Optional을 반환하므로 orElseThrow()를 사용합니다.
        ShippingInfo info = shippingInfoRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("운송장 번호를 찾을 수 없습니다."));

        info.setStatus(newStatus);
        // @PreUpdate 덕분에 updatedAt은 자동으로 갱신됩니다.
        return new ShippingResponseDto(shippingInfoRepository.save(info));
    }
}