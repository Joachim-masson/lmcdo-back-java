package com.lmcdo.lmcdoBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Autorise toutes les routes du back
                        .allowedOrigins("http://localhost:5173") // Ton front Vite
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") //Ajout de PATCH et OPTIONS
                        .allowedHeaders("*") // Autorise tous les headers (Content-Type, Authorization, etc.)
                        .allowCredentials(true);
            }
        };
    }
}
