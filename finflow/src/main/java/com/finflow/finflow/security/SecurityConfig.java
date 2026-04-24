package com.finflow.finflow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // desativa CSRF (necessário pra API)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // liberei TODAS as rotas para teste professor.
            );

        return http.build();
    }
}