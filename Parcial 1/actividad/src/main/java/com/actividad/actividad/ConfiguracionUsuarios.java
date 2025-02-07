package com.actividad.actividad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // ¡Esta anotación es crítica!
public class ConfiguracionUsuarios {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails usuario = User.builder()
                .username("usuario")
                .password(passwordEncoder().encode("contraseña")) // Contraseña encriptada
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(usuario);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}