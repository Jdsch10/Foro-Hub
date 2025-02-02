package com.foro.hub.config;

import com.foro.hub.filter.JwtAuthenticationFilter;
import com.foro.hub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir login sin autenticación
                .antMatchers("/topicos/**").authenticated() // Solo usuarios autenticados pueden acceder a los tópicos
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(tokenService)) // Filtro para manejar la autenticación JWT
                .httpBasic().disable()
                .formLogin().disable();
    }
}