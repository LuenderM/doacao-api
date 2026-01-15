package com.doacao.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(
                // O endereço do seu projeto no Lovable
                "https://0309684b-cda9-40b0-8421-8b28d49482b9.lovableproject.com",
                // O endereço de preview do Lovable
                "https://id-preview--0309684b-cda9-40b0-8421-8b28d49482b9.lovable.app",
                // Permite localhost para seus testes locais também
                "http://localhost:8080",
                "http://localhost:3000"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}
