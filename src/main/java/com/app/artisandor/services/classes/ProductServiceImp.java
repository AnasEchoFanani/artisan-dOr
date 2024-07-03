package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Product;
import com.app.artisandor.services.interfaces.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImp implements ProductService {
    @Override
    public Page<Product> getProduct(Pageable pageable) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
