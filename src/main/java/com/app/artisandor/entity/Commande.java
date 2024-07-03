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
    /**
     * The unique identifier of the commande.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who placed the commande.
     * It is a ManyToOne relationship with the User entity.
     * The fetch type is LAZY to improve performance.
     * The ToString.Exclude annotation is used to prevent infinite recursion in toString() method.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    /**
     * The products included in the commande.
     * It is a ManyToMany relationship with the Product entity.
     * The fetch type is LAZY to improve performance.
     * The ToString.Exclude annotation is used to prevent infinite recursion in toString() method.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products;

    /**
     * The quantity of the commande.
     */
    private int qte;

    /**
     * The date and time when the commande was created.
     * It is automatically set by the database when the record is inserted.
     * The @Temporal annotation is used to specify the temporal type of the column.
     * The @Column annotation is used to define the column name, nullable, and updatable properties.
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}