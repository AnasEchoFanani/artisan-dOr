package com.app.artisandor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * This class is responsible for configuring Cross-Origin Resource Sharing (CORS) for the application.
 * CORS is a mechanism that allows a server to control access to resources from different origins.
 *
 * @author Anas FANANI
 * @version 1.0
 */
@Configuration
public class CorsConfig {

    /**
     * This method creates and returns a CorsFilter bean that will be used to handle CORS requests.
     *
     * @return a CorsFilter bean
     */
    @Bean
    public CorsFilter corsFilter() {
        // Create a URL-based CORS configuration source
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Create a new CORS configuration
        CorsConfiguration config = new CorsConfiguration();

        // Allow requests from any origin
        config.addAllowedOrigin("*");

        // Allow specific HTTP methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        // Allow specific headers
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("Authorization");

        // Register the CORS configuration for all paths
        source.registerCorsConfiguration("/**", config);

        // Return a new CorsFilter using the source
        return new CorsFilter(source);
    }
}
