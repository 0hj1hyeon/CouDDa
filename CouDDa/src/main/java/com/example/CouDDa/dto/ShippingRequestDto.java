package com.example.CouDDa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequestDto {
    private Long orderId; // 연결할 주문 ID
    private String receiverName; // 수령인 이름
    private String address; // 배송 주소
}