package com.issoft.coherent.shop.config;

import com.issoft.coherent.shop.model.Role;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/v1/product/all").permitAll()
                .pathMatchers(HttpMethod.POST,"/v1/product/add").permitAll()
                .pathMatchers(HttpMethod.GET,"/v1/user/all").hasRole(Role.ADMIN.toString())
                .matchers(EndpointRequest.toAnyEndpoint()).hasRole(Role.ADMIN.toString())
                .anyExchange().permitAll()
                .and().formLogin()
                .and().httpBasic()
                .and().build();
    }


    @SuppressWarnings("deprecation") // Removes warning from "withDefaultPasswordEncoder()"
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        return new MapReactiveUserDetailsService(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("1")
                        .roles(Role.USER.toString())
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("1")
                        .roles(Role.ADMIN.toString(), Role.USER.toString())
                        .build());
    }
}
