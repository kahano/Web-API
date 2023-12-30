package com.project.custom_product.Auth;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum Role {
    
    ADMIN(EnumSet.of(
            UserPermission.CUSTOMER_READ,
            UserPermission.CUSTOMER_WRITE,
            UserPermission.PRODUCT_READ,
            UserPermission.PRODUCT_WRITE,
            UserPermission.PURCHASE_READ
            //UserPermission.PURCHASE_WRITE
    )),
    
    //CUSTOMER(EnumSet.noneOf(UserPermission.class));
    CUSTOMER(EnumSet.of(
            UserPermission.CUSTOMER_READ,
            UserPermission.CUSTOMER_WRITE,
            UserPermission.PRODUCT_READ,
            UserPermission.PURCHASE_READ,
            UserPermission.PURCHASE_WRITE
            ));

    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> p){

        this.permissions = p;
    }

    public Set<UserPermission> getPermissions(){

        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> get_GrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                        .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
        
    }
}
