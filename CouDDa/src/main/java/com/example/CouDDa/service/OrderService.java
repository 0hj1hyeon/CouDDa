package com.example.CouDDa.service;

import com.example.CouDDa.domain.Order;
import com.example.CouDDa.domain.OrderItem;
import com.example.CouDDa.domain.Product;
import com.example.CouDDa.domain.User;
import com.example.CouDDa.dto.OrderRequestDto;
import com.example.CouDDa.repository.OrderRepository;
import com.example.CouDDa.repository.ProductRepository;
import com.example.CouDDa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // 필요한 Repository 의존성 선언
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional // 데이터를 변경(저장)하는 메서드이므로 트랜잭션 처리
    public Order createOrder(Long userId, OrderRequestDto orderRequestDto) {
        // 1. 회원 검증: 주문을 요청한 회원이 존재하는지 확인 (없으면 비즈니스 오류 발생)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다."));

        // 2. 주문 상품 목록 생성 및 검증: 요청 받은 상품 ID들을 기반으로 재고 확인 및 OrderItem 엔티티 생성
        List<OrderItem> orderItems = createOrderItems(orderRequestDto);

        // 3. 총 주문 금액 계산
        Integer totalAmount = calculateTotalAmount(orderItems);

        // 4. Order 엔티티 생성 및 초기화
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("PAID"); // 임시로 결제 완료 상태 지정

        // 5. OrderItem과 Order 연결 및 최종 저장
        order.setOrderItems(orderItems);
        orderItems.forEach(item -> item.setOrder(order));

        return orderRepository.save(order);
    }


    private List<OrderItem> createOrderItems(OrderRequestDto orderRequestDto) {
        return orderRequestDto.getOrderItems().stream()
                .map(requestItem -> {

                    // 1. 상품 존재 검증: 주문하려는 상품 ID가 유효한지 확인
                    Product product = productRepository.findById(requestItem.getProductId())
                            .orElseThrow(() -> new IllegalStateException("상품을 찾을 수 없습니다: " + requestItem.getProductId()));

                    // 2. 재고 부족 검증
                    if (product.getStockQuantity() < requestItem.getQuantity()) {
                        throw new IllegalStateException("재고가 부족합니다: " + product.getName());
                    }
                    // 3. 재고 감소
                    product.setStockQuantity(product.getStockQuantity() - requestItem.getQuantity());
                    productRepository.save(product); // 변경된 재고 저장

                    // 4. OrderItem 엔티티 생성
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(requestItem.getQuantity());
                    orderItem.setPriceAtPurchase(product.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    private int calculateTotalAmount(List<OrderItem> orderItems) {
        // Integer로 변경 가정
        return orderItems.stream()
                .mapToInt(item -> item.getPriceAtPurchase() * item.getQuantity())
                .sum();
    }
}