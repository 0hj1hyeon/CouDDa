package com.example.CouDDa.controller;

import com.example.CouDDa.domain.Seller;
import com.example.CouDDa.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /**
     * POST /api/sellers
     * 새로운 판매자 등록
     */
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller newSeller = sellerService.createSeller(seller);
        return ResponseEntity.ok(newSeller);
    }

    /**
     * GET /api/sellers/{id}
     * 특정 ID의 판매자 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }
}