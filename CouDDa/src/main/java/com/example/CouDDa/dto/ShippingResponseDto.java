package com.example.CouDDa.dto;

import com.example.CouDDa.domain.ShippingInfo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ShippingResponseDto {
    private final Long id;
    private final Long orderId;
    private final String receiverName;
    private final String address;
    private final String trackingNumber;
    private final String status;
    private final LocalDateTime updatedAt;

    public ShippingResponseDto(ShippingInfo shippingInfo) {
        this.id = shippingInfo.getId();
        this.orderId = shippingInfo.getOrder().getId(); // Order 객체에서 ID 추출
        this.receiverName = shippingInfo.getReceiverName();
        this.address = shippingInfo.getAddress();
        this.trackingNumber = shippingInfo.getTrackingNumber();
        this.status = shippingInfo.getStatus();
        this.updatedAt = shippingInfo.getUpdatedAt();
    }
}