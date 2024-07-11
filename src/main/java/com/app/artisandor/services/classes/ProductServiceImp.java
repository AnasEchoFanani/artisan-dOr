package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Product;
import com.app.artisandor.exception.ProductNotFoundException;
import com.app.artisandor.exception.ProductValidationException;
import com.app.artisandor.repository.ProductRepository;
import com.app.artisandor.services.interfaces.ProductService;
import jakarta.validation.Valid;
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
public class ProductServiceImp implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);

    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProduct(Pageable pageable) {
        logger.info("Fetching products with pageable: {}", pageable);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            logger.warn("Product with id {} not found", id);
            throw new ProductNotFoundException("Product not found for id: " + id);
        }
    }

    @Override
    @Transactional
    public Product saveProduct(@Valid Product product) {
        logger.info("Saving product: {}", product);

        validateProduct(product);

        checkForDuplicateName(product);

        try {
            Product savedProduct = productRepository.save(product);
            logger.info("Product saved successfully: {}", savedProduct);
            return savedProduct;
        } catch (Exception e) {
            logger.error("Error saving product: {}", product, e);
            throw new RuntimeException("Failed to save product", e);
        }
    }

    private void validateProduct(Product product) {
        if (product.getNomProduit() == null || product.getNomProduit().isEmpty()) {
            logger.warn("Product name is null or empty");
            throw new ProductValidationException("Product name cannot be null or empty");
        }
        if (product.getPrix() == null || product.getPrix() <= 0) {
            logger.warn("Product price is null or invalid");
            throw new ProductValidationException("Product price must be greater than zero");
        }
        // Add other validation checks as necessary
    }

    private void checkForDuplicateName(Product product) {
        boolean exists = productRepository.existsByName(product.getNomProduit());
        if (exists) {
            logger.warn("Product with name {} already exists", product.getNomProduit());
            throw new ProductValidationException("Product with name '" + product.getNomProduit() + "' already exists");
        }
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        logger.info("Deleting product with id: {}", id);
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting product with id: {}", id, e);
            throw new RuntimeException("Failed to delete product", e);
        }
    }
}
