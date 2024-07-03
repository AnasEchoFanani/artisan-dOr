package com.app.artisandor.repository;

import com.app.artisandor.entity.Comments;
import com.app.artisandor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByProduct(Product product);
}