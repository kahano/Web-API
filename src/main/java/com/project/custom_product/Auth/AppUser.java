package com.project.custom_product.Auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;



@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AppUser implements UserDetails {

  

    
    private String username;

    private String password;

    // @Enumerated(EnumType.STRING)
    // private Role role;

    private final Set<? extends GrantedAuthority> grantedAuthorities;

    private final boolean locked;

    private final boolean enable;

    private final boolean is_accountExpired;

    private final boolean is_credentialsExpired;

    // private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
       return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return is_accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
       return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return is_credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
    
}
