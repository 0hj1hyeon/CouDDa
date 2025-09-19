package com.example.CouDDa.repository;

import com.example.CouDDa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom queries for Category entities go here.
}
