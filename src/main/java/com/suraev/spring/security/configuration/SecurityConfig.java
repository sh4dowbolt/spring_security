package com.suraev.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
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
                .roles("Manager", "HR").build());

        return manager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/").hasAnyRole("Employee", "HR", "Manager")
                .requestMatchers("/hr_info").hasRole("HR")
                .requestMatchers("/manager_info").hasRole("Manager")
                .and().formLogin(formlogin -> formlogin.permitAll());
        return http.build();
    }
    }



