package com.app.artisandor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Represents a category entity in the application.
 * This entity is mapped to a database table using JPA annotations.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category {

    /**
     * The unique identifier of the category.
     * It is auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the category.
     */
    private String categoryName;

    /**
     * The timestamp of when the category was created.
     * It is automatically set by the database when a new category is inserted.
     *
     * @Column(name = "created_at", nullable = false, updatable = false)
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    /**
     * The timestamp of when the category was last modified.
     * It is automatically set by the database when a category is updated.
     *
     * @Column(name = "modified_at")
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}