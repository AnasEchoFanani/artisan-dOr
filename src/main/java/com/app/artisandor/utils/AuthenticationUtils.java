package com.app.artisandor.utils;

import com.app.carrentalmongodb.entities.Users;
import com.app.carrentalmongodb.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationUtils {

    private final UsersRepository usersRepository;

    public String getUserEmailFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
        }
        return null;
    }

    public Users getUserFromAuthentication() {
        String email = getUserEmailFromAuthentication();
        if (email != null) {
            Optional<Users> users = usersRepository.findByEmail(email);
            return users.orElse(null);
        }
        return null;
    }
}
