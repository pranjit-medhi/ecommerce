package com.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
    private  final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
       return   http.csrf(AbstractHttpConfigurer::disable)
               .cors(AbstractHttpConfigurer::disable)
               .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
                .authorizeHttpRequests( request->
                    request.requestMatchers("/product").permitAll()
                                    .requestMatchers("/auth/register").permitAll()
                                    .requestMatchers("/auth/login").permitAll()
                            .anyRequest().authenticated()
                ).build();
    }

}
