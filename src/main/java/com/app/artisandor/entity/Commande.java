package com.app.artisandor.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a Commande entity in the application.
 * It is annotated with JPA annotations to define its mapping to the database.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products;

    private int qte;
    private String paymentMethod;
    private String paymentIdendifier;
    private Double totalAmount;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}