package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Category;
import com.app.artisandor.repository.CategoryRepository;
import com.app.artisandor.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> getCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryById(Long id) throws IllegalArgumentException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found for id: " + id));
    }

    @Override
    public Category saveCategory(Category category) throws IllegalArgumentException {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) throws IllegalArgumentException {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found for id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
