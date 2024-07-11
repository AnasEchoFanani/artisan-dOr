package com.app.artisandor.controllers;

import com.app.artisandor.entity.Comments;
import com.app.artisandor.entity.Product;
import com.app.artisandor.services.interfaces.CommentsService;
import com.app.artisandor.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;
    private final ProductService productService;

    @Autowired
    public CommentsController(CommentsService commentsService, ProductService productService) {
        this.commentsService = commentsService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Comments>> getComments(Pageable pageable) {
        Page<Comments> comments = commentsService.getComments(pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comments> getCommentsById(@PathVariable Long id) {
        Comments comments = commentsService.getCommentsById(id);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comments> createComments(@Valid @RequestBody Comments comments) {
        Comments savedComments = commentsService.saveComments(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable Long id) {
        commentsService.deleteCommentsById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Comments>> getCommentsByProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        List<Comments> comments = commentsService.getCommentsByProduct(product);
        return ResponseEntity.ok(comments);
    }
}
