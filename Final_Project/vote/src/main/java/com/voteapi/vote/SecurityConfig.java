package com.voteapi.vote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .authorizeRequests()
            .requestMatchers("/api/test/user").authenticated() // Only authenticated users can access /user
            .anyRequest().permitAll() // All other requests are allowed without authentication
            .and()
            .formLogin().permitAll() // Configure form login if needed
            .and()
            .logout().permitAll();

        // Add more configurations as required

        return http.build();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RequestForwardingFilter requestForwardingFilter() {
        return new RequestForwardingFilter(restTemplate());
    }

    // Other beans and configurations...    

    // Other beans and configurations (like UserDetailsService, PasswordEncoder) go here
}
