package com.issoft.coherent.shop.config;

import com.issoft.coherent.shop.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/v1/product/all").permitAll()
                .pathMatchers(HttpMethod.POST, "/v1/product/add").permitAll()
                .pathMatchers(HttpMethod.GET, "/v1/user/all").hasRole(Role.ADMIN.toString())
                .anyExchange().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
