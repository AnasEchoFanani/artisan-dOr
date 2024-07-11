package com.app.artisandor.dto;

import com.app.artisandor.enums.Roles;
import com.app.artisandor.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.app.artisandor.entity.User}
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private LocalDateTime dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
    private Roles role;
    private String picture;
    private LocalDateTime deletedAt;
    private String deletedBy;
}
