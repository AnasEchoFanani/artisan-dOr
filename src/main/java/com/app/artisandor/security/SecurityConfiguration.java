package com.app.artisandor.security;

import com.app.artisandor.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is responsible for configuring the security settings for the application.
 * It sets up authentication providers, CSRF protection, session management, and adds the JWT authentication filter.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * The JWT authentication filter that will be added to the security filter chain.
     */
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * The authentication provider that will be used for authenticating users.
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * This method configures the security filter chain for the application.
     *
     * @param http The HttpSecurity object used to configure security settings.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable)
                // Configure request authorization
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        // Allow all requests to the API
                                        .requestMatchers("/**")
                                        .permitAll()
                                        // Any other request requires authentication
                                        .anyRequest()
                                        .authenticated()
                )
                // Configure session management to be stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Add the authentication provider
                .authenticationProvider(authenticationProvider);

        // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Build and return the configured SecurityFilterChain
        return http.build();
    }
}
