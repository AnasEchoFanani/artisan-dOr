package com.app.artisandor.repository;

import com.app.artisandor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select (count(p) > 0) from Product p where p.nomProduit = ?1")
    boolean existsByName(String nomProduit);
}