package com.example.CouDDa.service;

import com.example.CouDDa.domain.Seller;
import com.example.CouDDa.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    /**
     * 새로운 판매자를 등록하고 저장합니다.
     * @param seller 판매자 정보 객체
     * @return 저장된 판매자 객체
     */
    public Seller createSeller(Seller seller) {
        seller.setCreatedAt(LocalDateTime.now());
        return sellerRepository.save(seller);
    }

    /**
     * 특정 ID를 가진 판매자를 조회합니다.
     * @param id 판매자 ID
     * @return 조회된 판매자 객체
     */
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("판매자를 찾을 수 없습니다. ID: " + id));
    }
}