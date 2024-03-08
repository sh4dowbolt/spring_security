package com.suraev.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Autowired
    DataSource dataSource;
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return  users;



        /*InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
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

        return manager;*/

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .requestMatchers("/hr_info").hasRole("HR")
                .requestMatchers("/manager_info").hasRole("MANAGER")
                .and().formLogin(formlogin -> formlogin.permitAll());
        return http.build();
    }
    }



