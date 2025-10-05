package com.example.CouDDa.controller;

import com.example.CouDDa.dto.ShippingRequestDto;
import com.example.CouDDa.dto.ShippingResponseDto;
import com.example.CouDDa.service.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /**
     * POST /api/shipping
     * 새로운 배송 정보 등록 (주문 발생 시 호출)
     */
    @PostMapping
    public ResponseEntity<ShippingResponseDto> createShipping(@RequestBody ShippingRequestDto requestDto) {
        ShippingResponseDto response = shippingService.createShippingInfo(requestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/shipping/order/{orderId}
     * 특정 주문 ID의 배송 정보 조회
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShippingResponseDto> getShippingInfoByOrderId(@PathVariable Long orderId) {
        ShippingResponseDto response = shippingService.getShippingInfoByOrderId(orderId);
        return ResponseEntity.ok(response);
    }

    /**
     * PUT /api/shipping/status/{trackingNumber}
     * 배송 상태 업데이트 (물류 시스템에서 호출된다고 가정)
     */
    @PutMapping("/status/{trackingNumber}")
    public ResponseEntity<ShippingResponseDto> updateShippingStatus(@PathVariable String trackingNumber,
                                                                    @RequestParam String newStatus) {
        ShippingResponseDto response = shippingService.updateShippingStatus(trackingNumber, newStatus);
        return ResponseEntity.ok(response);
    }
}