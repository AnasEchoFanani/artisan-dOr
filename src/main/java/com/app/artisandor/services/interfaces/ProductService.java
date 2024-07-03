package com.app.artisandor.services.interfaces;

import com.app.artisandor.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the Product Service.
 * It provides methods for performing CRUD operations on Product entities.
 */
public interface ProductService {

    /**
     * Retrieves a paginated list of products.
     *
     * @param pageable The pagination information.
     * @return A Page object containing the list of products.
     */
    Page<Product> getProduct(Pageable pageable);

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return The product with the specified id, or null if not found.
     */
    Product getProductById(Long id);

    /**
     * Saves a new product or updates an existing product.
     *
     * @param product The product to be saved or updated.
     * @return The saved product.
     */
    Product saveProduct(Product product);

    /**
     * Deletes a product by its unique identifier.
     *
     * @param id The unique identifier of the product.
     */
    void deleteProductById(Long id);
}
