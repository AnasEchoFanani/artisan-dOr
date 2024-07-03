package com.app.artisandor.filters;

import com.app.artisandor.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * This class is a custom filter for Spring Security that handles JWT authentication.
 * It extends OncePerRequestFilter to ensure that the filter is only executed once per request.
 *
 * @author Anas FANANI
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * The JwtService instance used for JWT operations.
     */
    private final JwtService jwtService;

    /**
     * The UserDetailsService instance used to load user details by username.
     */
    private final UserDetailsService userDetailsService;

    /**
     * This method is called for each request and performs JWT authentication.
     * It extracts the JWT from the request header, validates it, and sets the authentication context.
     *
     * @param request The incoming HTTP request.
     * @param response The outgoing HTTP response.
     * @param filterChain The filter chain to continue processing the request.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an error occurs during request processing.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Check if the request contains a valid JWT in the Authorization header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If no JWT is present, continue with the next filter in the chain
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT from the Authorization header
        jwt = authHeader.substring(7);

        // Extract the user email from the JWT
        userEmail = jwtService.extractUsername(jwt);

        // Check if the user email is present and if there is no existing authentication context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details by the user email
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Validate the JWT against the user details
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Create a new authentication token with the user details
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Set the authentication details in the authentication token
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Set the authentication context with the authentication token
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}