package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Category;
import com.app.artisandor.services.interfaces.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryServiceImp implements CategoryService {
    @Override
    public Page<Category> getCategory(Pageable pageable) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Category saveCategory(Category category) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) throws IllegalArgumentException {

    }
}
