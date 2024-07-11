package com.app.artisandor.utils;

import com.app.artisandor.entity.User;
import com.app.artisandor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AuthenticationUtils {

    private final UserRepository usersRepository;

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

    public User getUserFromAuthentication() {
        String email = getUserEmailFromAuthentication();
        if (email != null) {
            Optional<User> users = usersRepository.findByEmail(email);
            return users.orElse(null);
        }
        return null;
    }
}
