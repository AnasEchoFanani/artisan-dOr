package com.app.artisandor.services.interfaces;

import com.app.artisandor.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for Category Service.
 * It provides methods for performing CRUD operations on Category entity.
 */
public interface CategoryService {

    /**
     * Retrieves a paginated list of Category entities.
     *
     * @param pageable The pagination information.
     * @return A Page object containing the list of Category entities.
     */
    Page<Category> getCategory(Pageable pageable);

    /**
     * Retrieves a Category entity by its unique identifier.
     *
     * @param id The unique identifier of the Category entity.
     * @return The Category entity with the specified id.
     * @throws IllegalArgumentException If the id is null.
     */
    Category getCategoryById(Long id) throws IllegalArgumentException;

    /**
     * Saves a new or updates an existing Category entity.
     *
     * @param category The Category entity to be saved or updated.
     * @return The saved or updated Category entity.
     * @throws IllegalArgumentException If the category is null.
     */
    Category saveCategory(Category category) throws IllegalArgumentException;

    /**
     * Deletes a Category entity by its unique identifier.
     *
     * @param id The unique identifier of the Category entity to be deleted.
     * @throws IllegalArgumentException If the id is null.
     */
    void deleteCategoryById(Long id) throws IllegalArgumentException;
}
