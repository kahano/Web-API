package com.project.custom_product.Auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.project.custom_product.security.PasswordEncoder;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AppUserServiceDao implements AppUserRepository {

    @Autowired
    private final  BCryptPasswordEncoder passwordencoder;

     private List<AppUser> get_AppUsers(){
       
        List<AppUser> appUserList = List.of(

            new AppUser("user_pass",
            passwordencoder.encode("kahano243"),
            Role.CUSTOMER.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            ),

            new AppUser("admin_pass",
            passwordencoder.encode("kahano1988"),
            Role.ADMIN.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            )
        
         );

         return appUserList;
        
    }
    

    @Override
    public Optional<AppUser> findByUsername(String username){
        
        return get_AppUsers()
            .stream()
            .filter(appUser -> username.equals(appUser.getUsername()))
            .findFirst();
    }

    
}
