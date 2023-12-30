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

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.custom_product.Auth.CustomUserdetailsService;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class webSecurityConfig {
    
    @Autowired
    private final PasswordEncoder passwordencoder;

    @Autowired
    private final CustomUserdetailsService user_service;

    


    @Bean
  
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/","index","/css/*", "/js/*").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll()
                
            )
            
            .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID", "remember-me")
            .logoutSuccessUrl("/login")
            )
         
            .rememberMe(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());


        return http.build();
    

        
    }

    // @Bean
    // PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder(10);
    // }

    // @Bean 
    // public UserDetailsService users(){
        
    //     UserDetails admin = User.builder()
    //     .username("admin_pass")
    //     .password(this.passwordEncoder().encode("kahano1988"))
    //      .authorities(Role.ADMIN.get_GrantedAuthorities())
    //     .build();

        
    //     UserDetails user  = User.builder()
    //     .username("user_pass")
    //     .password(this.passwordEncoder().encode("kahano243"))
    //     .authorities(Role.CUSTOMER.get_GrantedAuthorities())
    //     .build();
    //     System.out.println("hashed password is: " + user.getPassword());

    //     return new InMemoryUserDetailsManager(admin,user);


    // }

    @Bean
    public AuthenticationManager authManager(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(user_service);
        daoProvider.setPasswordEncoder(passwordencoder);
        return new ProviderManager(daoProvider);
    }


    




}
