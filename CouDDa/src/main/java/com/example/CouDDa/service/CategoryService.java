package com.example.CouDDa.service;

import com.example.CouDDa.domain.Category;
import com.example.CouDDa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 새로운 카테고리를 생성하고 저장합니다.
     * @param category 카테고리 정보 객체
     * @return 저장된 카테고리 객체
     */
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * 모든 카테고리 목록을 조회합니다.
     * @return 카테고리 목록
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * 특정 ID를 가진 카테고리를 조회합니다.
     * @param id 카테고리 ID
     * @return 조회된 카테고리 객체
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다. ID: " + id));
    }
}