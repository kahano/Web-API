package com.project.custom_product.security.config;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.custom_product.Jwt_Auth.JwtAuthEntryPoint;
import com.project.custom_product.Jwt_Auth.JwtAuthenticationFilter;
import com.project.custom_product.Jwt_Auth.JwtConfig;
import com.project.custom_product.User.CustomUserdetailsService;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class webSecurityConfig {

    private final JwtAuthEntryPoint auth_entrypoint;
    

    private final PasswordEncoder passwordencoder;


    private final CustomUserdetailsService user_service;

    private final JwtConfig jwtconfig;

    


    @Bean
  
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception 
                        .authenticationEntryPoint(auth_entrypoint))
            
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/","index","/css/*", "/js/*").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                //.requestMatchers("/api/v1/**").permitAll()
                // .requestMatchers("/api/v2/**").permitAll()
                .anyRequest().authenticated())
    

         
            .rememberMe(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    

        
    }

  
    @Bean
    public AuthenticationManager authManager(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(user_service);
        daoProvider.setPasswordEncoder(passwordencoder);
        return new ProviderManager(daoProvider);
    }

    @Bean
    public  JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(user_service,jwtconfig);
    }

   

    




}
