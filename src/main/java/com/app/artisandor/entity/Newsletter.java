package com.app.artisandor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a Newsletter entity in the application.
 * This class is annotated with JPA annotations to define its mapping to the database.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Newsletter {

    /**
     * The unique identifier of the Newsletter.
     * It is annotated with JPA annotations to define its mapping to the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The email of the subscriber.
     * This field is not annotated with JPA annotations as it is not mapped to the database.
     */
    private String email;
}
