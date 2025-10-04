package com.example.CouDDa.controller;

import com.example.CouDDa.domain.Order;
import com.example.CouDDa.dto.OrderRequestDto;
import com.example.CouDDa.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // OrderService를 주입받습니다.
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createOrder(
            @PathVariable Long userId,
            @RequestBody OrderRequestDto orderRequestDto) {
        try {
            // 주문 요청을 서비스로 비즈니스 로직 실행
            Order order = orderService.createOrder(userId, orderRequestDto);

            // 성공하면 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(order);

        } catch (IllegalStateException e) {
            // 재고 부족, 상품 없음 등 오류 시 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            // 기타 오류들 발생하면 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 처리 중 서버 오류가 발생했습니다.");
        }
    }

}