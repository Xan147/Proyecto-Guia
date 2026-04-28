package com.project.shop.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//Dice que esta clase contiene configuraciones
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); //De donde vendran las solicitudes
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); //Que metodos permite
        configuration.setAllowedHeaders(List.of("*")); //Permite todo tipo de encabezados
        configuration.setAllowCredentials(true); //Permite enviar cookies o encabezados de autenticación

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration); //Aplica esta configuración a todas las rutas que comiencen con /api/
        return source; //Retorna la configuración de CORS para que Spring Security la use
    }
}
