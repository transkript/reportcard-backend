package com.transkript.reportcard.config.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private final String[] ALLOWED_ORIGINS = new String[]{
            "http://localhost:8080",
            "http://localhost:4200",
            "http://localhost:8000",
            "http://localhost:4200",
            "http://localhost:3000"
    };
    private final String[] ALLOWED_METHODS = new String[]{
            "GET", "POST", "PUT", "DELETE"
    };

    private final String[] ALLOWED_HEADERS = new String[]{
            "Authorization", "Cache-Control", "Content-Type", "Access-Control-Request-Headers", "X-Auth-Token",
            "Access-Control-Request-Method", "Access-Control-Allow-Headers", "Accept", "Access-Control-Allow-Origin"
    };

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedMethods(ALLOWED_METHODS)
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedHeaders(ALLOWED_HEADERS);
    }
}
