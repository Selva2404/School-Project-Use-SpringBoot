package org.easyschool.config;


import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class securityconfig {

        @Bean
        SecurityFilterChain defaultsecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**"));
                http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/contact/**").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/courses/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/holidays").permitAll()
                )
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true).failureUrl("/login?error=true"));
                http.headers(headers ->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
        }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder ) {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("12345"))
                .roles("USER")
                .build();
        UserDetails admin =User.builder()
                .username("admin")
                .password(passwordEncoder.encode("54321"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    }


