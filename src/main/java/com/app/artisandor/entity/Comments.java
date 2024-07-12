package com.app.artisandor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * This class represents a Comments entity in the application.
 * It is annotated with JPA annotations to define its mapping to the database.
 * It also includes fields for comment text, stars, product, user, and timestamps.
 *
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Comments {
    /**
     * The unique identifier for each comment.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The comment text.
     */
    private String comment;

    /**
     * The number of stars given to the comment.
     */
    private int stars;

    /**
     * The product associated with the comment.
     * It is a ManyToOne relationship with the Product entity.
     * The fetch type is LAZY to improve performance.
     */
    @ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.LAZY)
    private Product product;

    /**
     * The user who made the comment.
     * It is a OneToOne relationship with the User entity.
     */
    @OneToOne
    private User user;

    /**
     * The timestamp of when the comment was created.
     * It is automatically set by the database when the comment is inserted.
     * It is marked as nullable = false and updatable = false to ensure data integrity.
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp of when the comment was last modified.
     * It is automatically updated by the database when the comment is updated.
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}