package com.app.artisandor.security;


import com.app.artisandor.entity.User;
import com.app.artisandor.enums.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This class provides methods for generating and validating JWT tokens.
 */
@Service
public class JwtService {
    /**
     * Secret key for signing JWT tokens.
     */
    private static final String SECRET_KEY = "3ff7fc1eed5d1a4c4bbebf033af39964f7d9144c90b94354a2814f3647542e93";

    /**
     * Extracts the username from the given JWT token.
     *
     * @param token The JWT token to extract the username from.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the given JWT token.
     *
     * @param token The JWT token to extract the claim from.
     * @param claimsResolver A function that resolves the claim from the token.
     * @return The extracted claim.
     */
    public<T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails The user details to generate the token for.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token for the given user details and extra claims.
     *
     * @param extraClaims Extra claims to include in the token.
     * @param userDetails The user details to generate the token for.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        long expirationMillis;

        // Check if user has ROLE_CUSTOMER or ROLE_GUEST
        if (userDetails instanceof User user) {
            if (user.getRole() == Roles.ROLE_CUSTOMER) {
                // Set expiration to one month (30 days)
                expirationMillis = 1000L * 60 * 60 * 24 * 30;
            } else {
                // Set expiration to 24 hours
                expirationMillis = 1000L * 60 * 60 * 24;
            }
        } else {
            // Default expiration to 24 hours if UserDetails is not an instance of Users
            expirationMillis = 1000L * 60 * 60 * 24;
        }

        // Build and sign the JWT token
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates if the given JWT token is valid for the given user details.
     *
     * @param token The JWT token to validate.
     * @param userDetails The user details to validate the token against.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token The JWT token to check.
     * @return True if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token The JWT token to extract the expiration date from.
     * @return The expiration date extracted from the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token The JWT token to extract the claims from.
     * @return The extracted claims.
     */
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder().
                setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Gets the signing key for JWT token signing.
     *
     * @return The signing key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
