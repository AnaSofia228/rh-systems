package com.rh_systems.service_discovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Eureka Server security settings
 */
@Configuration
public class EurekaServerConfig {

    /**
     * Configures the security filter chain for the Eureka Server
     *
     * @param http The HttpSecurity object to configure
     * @return The configured SecurityFilterChain
     * @throws Exception If there's an error in the security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable)
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests to Eureka endpoints
                        .requestMatchers("/eureka/**").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated())
                // Enable basic authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}