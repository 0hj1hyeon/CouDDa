package com.example.CouDDa.repository;

import com.example.CouDDa.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    // Custom queries for Seller entities go here.
}