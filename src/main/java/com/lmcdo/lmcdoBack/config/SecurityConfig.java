package com.lmcdo.lmcdoBack.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Activer le support CORS avec notre configuration personnalisée
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // 2. Désactiver le CSRF pour les API REST stateless
            .csrf(csrf -> csrf.disable())
            
            // 3. Autoriser les requêtes (temporairement tout autoriser en attendant JWT/Sessions)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Autorise le frontend React Vite
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        
        // Autorise les méthodes HTTP nécessaires
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        
        // Autorise tous les headers (Content-Type, Authorization, etc.)
        configuration.setAllowedHeaders(List.of("*"));
        
        // Autorise l'envoi de cookies/credentials
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Applique la configuration à toutes les routes
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
