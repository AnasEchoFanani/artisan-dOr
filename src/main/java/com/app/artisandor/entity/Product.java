package com.app.artisandor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * This class represents a product entity in the application.
 * It is annotated with JPA annotations to define its mapping to the database.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Entity
@Setter
@Getter
public class Product {
    /**
     * The unique identifier of the product.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    private String nomProduit;

    /**
     * The quantity of the product.
     */
    private int qnt;

    /**
     * The price of the product.
     */
    private Double prix;

    /**
     * The first image of the product.
     * It is stored as a byte array.
     */
    private byte[] image_1;

    /**
     * The second image of the product.
     * It is stored as a byte array.
     */
    private byte[] image_2;

    /**
     * The third image of the product.
     * It is stored as a byte array.
     */
    private byte[] image_3;

    /**
     * The category of the product.
     * It is a relationship with the Category entity.
     */
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private  Category category;

    /**
     * The date and time when the product was created.
     * It is automatically set by the database when the product is inserted.
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the product was last modified.
     * It is automatically set by the database when the product is updated.
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    /**
     * The date and time when the product will be released.
     * It is nullable, meaning it can be null if the release date is not set.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime releaseDate;
}
