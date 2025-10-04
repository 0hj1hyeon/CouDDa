package com.example.CouDDa.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    // 주문 시 요청 본문에 담겨 올 주문 상품 목록
    private List<OrderItemRequestDto> orderItems;
}