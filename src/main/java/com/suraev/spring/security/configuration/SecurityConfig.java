package com.suraev.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("Vitaly")
                .password("Vitaly")
                .roles("Employee").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("Andrey")
                .password("Shustikov")
                .roles("HR").build());
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("Dmitry")
                .password("Alekseev")
                .roles("Employee", "HR").build());

        return manager;

    }
    }



