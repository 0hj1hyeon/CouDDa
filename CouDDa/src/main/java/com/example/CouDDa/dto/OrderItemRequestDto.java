package com.example.CouDDa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDto {
    private Long productId; // 주문할 상품의 ID
    private Integer quantity; // 주문 수량
}