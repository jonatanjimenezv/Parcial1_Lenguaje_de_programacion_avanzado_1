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
                .formLogin(withDefaults())                   // Formulario de login
                .logout(logout -> logout                     // Configuración de logout
                        .logoutUrl("/logout")                    // Ruta para cerrar sesión
                        .logoutSuccessUrl("/publico")            // Redirige aquí después de cerrar sesión
                        .invalidateHttpSession(true)             // Invalida la sesión
                        .deleteCookies("JSESSIONID")             // Elimina la cookie de sesión
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}