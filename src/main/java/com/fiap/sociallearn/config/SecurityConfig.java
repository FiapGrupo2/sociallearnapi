package com.fiap.sociallearn.config;

import com.fiap.sociallearn.filter.AuthenticationFilter;
import com.fiap.sociallearn.filter.LoginFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**"
        // other public endpoints of your API may be appended to this array
};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
          .anyRequest().authenticated()
          .and()
          // Filter for the api/login requests
          .addFilterBefore(new LoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
          // Filter for other requests to check JWT in header
          .addFilterBefore(new AuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }
    
}