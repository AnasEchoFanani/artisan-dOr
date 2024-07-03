package com.app.artisandor.entity;

import com.app.artisandor.enums.Authority;
import com.app.artisandor.enums.Roles;
import com.app.artisandor.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Represents a user entity in the application. Implements UserDetails interface for Spring Security.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Unique email of the user.
     */
    @Column(unique = true)
    private String email;

    /**
     * Profile picture of the user.
     */
    private byte[] picture;

    /**
     * Encrypted password of the user.
     */
    private String password;

    /**
     * First name of the user.
     */
    private String firstName;

    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Date and time when the user was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date and time when the user was last updated.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    /**
     * Date and time when the user's information was last modified.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    /**
     * Date and time when the user was deleted.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    /**
     * User who deleted the record.
     */
    private String deletedBy;

    /**
     * Current status of the user.
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Role of the user.
     */
    @Enumerated(EnumType.STRING)
    private Roles role;

    /**
     * Set of authorities granted to the user.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn())
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
