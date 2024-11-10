package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Only disable if necessary; understand the implications
            .cors().and() // Enable CORS with default configuration or custom one below
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/userregister/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/orders/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Ensure roles are correctly set
                .requestMatchers("/api/events/**").permitAll()
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/contact/**").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/posts/**").permitAll()
                .requestMatchers("/api/payments/**").permitAll()
                
                .anyRequest().authenticated()
            )
            .httpBasic(); // Basic HTTP authentication (consider alternatives like JWT)
        
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Allow your frontend origin
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
