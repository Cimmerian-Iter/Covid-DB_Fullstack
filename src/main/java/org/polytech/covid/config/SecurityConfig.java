package org.polytech.covid.config;

import static org.springframework.security.config.Customizer.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http
        .securityMatcher("/api/**").authorizeHttpRequests((auth) -> auth
            .requestMatchers("/api/superadmin/**").hasAnyAuthority("ROLE_SUPERADMIN")
            .requestMatchers("/api/admin/**").hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN")
            .requestMatchers("/api/user/**").hasAnyAuthority("ROLE_SUPERADMIN", "ROLE_ADMIN", "ROLE_USER")
            .requestMatchers("/api/public/**").permitAll())
        .authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
        .httpBasic(withDefaults())
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable()) //Desactivation de la protection csrf
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//On rend les session stateless
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}