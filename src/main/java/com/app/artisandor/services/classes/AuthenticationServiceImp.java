package com.app.artisandor.services.classes;

import com.app.artisandor.entity.User;
import com.app.artisandor.enums.Roles;
import com.app.artisandor.repository.UserRepository;
import com.app.artisandor.security.JwtService;
import com.app.artisandor.services.interfaces.AuthenticationService;
import com.app.artisandor.utils.EntityToDtoConverterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.artisandor.dto.AuthenticationRequest;
import com.app.artisandor.dto.AuthenticationResponse;
import com.app.artisandor.dto.RegisterRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EntityToDtoConverterUtils entityToDtoConverterUtils;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            Optional<User> usersOptional = repository.findByEmail(request.getEmail());
            if (usersOptional.isPresent()) {
                return AuthenticationResponse.builder()
                        .token(null)
                        .user(null)
                        .error("Utilisateur déjà enregistré!")
                        .build();
            } else if (request.getPassword().isEmpty() || request.getPassword().isBlank()) {
                return AuthenticationResponse.builder()
                        .token(null)
                        .user(null)
                        .error("Veuillez entrer un mot de passe")
                        .build();
            } else {
                var user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Roles.ROLE_CUSTOMER)
                        .email(request.getEmail())
                        .createdAt(LocalDateTime.now())
                        .picture(request.getPicture())
                        .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .user(entityToDtoConverterUtils.convertToUsersDto(user))
                        .build();
            }
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .user(null)
                    .error("Erreur lors de l'inscription de l'utilisateur : " + e.getMessage())
                    .build();
        }
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            String jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(entityToDtoConverterUtils.convertToUsersDto(user))
                    .build();
        } catch (BadCredentialsException e) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .user(null)
                    .error("Email ou mots de passe incorrect")
                    .build();
        } catch (Exception e) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .user(null)
                    .error(e.getMessage())
                    .build();
        }
    }

    @Override
    public AuthenticationResponse defaultUser() {
        var user = repository.findByEmail("guest@carrental.com").orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(entityToDtoConverterUtils.convertToUsersDto(user))
                .build();
    }
}
