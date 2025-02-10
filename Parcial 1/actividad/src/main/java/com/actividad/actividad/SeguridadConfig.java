package com.actividad.actividad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SeguridadConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/publico").permitAll() // Ruta pública
                        .anyRequest().authenticated()            // Resto de rutas protegidas
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/privado", true)     // Redirigir después del login
                )
                .httpBasic(withDefaults());                  // Autenticación básica

        return http.build();
    }
}