package com.project.custom_product.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import com.project.custom_product.AppUser.Role;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class webSecurityConfig {
    
   
    


    @Bean
  
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
            .rememberMe(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());


        return http.build();
    

        
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean 
    public UserDetailsService users(){
        
        UserDetails admin = User.builder()
        .username("admin_pass")
        .password(this.passwordEncoder().encode("kahano1988"))
        //.roles(Role.ADMIN.name())
         .authorities(Role.ADMIN.get_GrantedAuthorities())
        .build();

        
        UserDetails user  = User.builder()
        .username("user_pass")
        .password(this.passwordEncoder().encode("kahano243"))
        //.roles(Role.CUSTOMER.name())
        .authorities(Role.CUSTOMER.get_GrantedAuthorities())
        .build();
        System.out.println("hashed password is: " + user.getPassword());

        return new InMemoryUserDetailsManager(admin,user);


    }


    




}
