package com.app.artisandor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomProduit;

    private int qnt;

    private Double prix;

    private String img;
    private Integer stock;
    private String status;
    private Integer costPerItem;
    private Integer bulkDiscountPrice;
    private Integer taxRate;
    private String brand;
    private String vendor;
    private String description;
    @OneToMany
    private List<Comments> categories;
    @OneToMany
    private List<Tags> tags;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<ImagesList> imagesLists;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private  Category category;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime releaseDate;
}
