package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Category;
import com.app.artisandor.repository.CategoryRepository;
import com.app.artisandor.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImp.class);

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategory(Pageable pageable) {
        try {
            Page<Category> categories = categoryRepository.findAll(pageable);
            return categories.isEmpty() ? Page.empty(pageable) : categories;
        } catch (Exception e) {
            logger.error("Failed to fetch categories: {}", e.getMessage(), e);
            return Page.empty(pageable);
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        try {
            return categoryRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found for id: " + id));
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Failed to fetch category by id: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while fetching category by id", e);
        }
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            logger.error("Failed to save category: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while saving category", e);
        }
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        if (id == null || !categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found for id: " + id);
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete category: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while deleting category", e);
        }
    }
}
