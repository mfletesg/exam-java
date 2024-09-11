package com.exam.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permitir CORS para todas las rutas
                        .allowedOrigins("http://localhost:5173") // Permitir solicitudes desde este origen
                        .allowedMethods("*") // Permitir todos los métodos (GET, POST, DELETE, etc.)
                        .allowedHeaders("*"); // Permitir todos los encabezados
            }
        };
    }
}