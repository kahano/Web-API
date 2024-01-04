package com.project.custom_product.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import lombok.AllArgsConstructor;


@AllArgsConstructor
@Repository("fake")
public class AppUserRepositoryDao implements fakeAppUserRepository {

    @Autowired
    private final  BCryptPasswordEncoder passwordencoder;

     private List<AppUser> get_AppUsers(){
       
        List<AppUser> appUserList = List.of(

    

            new AppUser(UUID.randomUUID(),
            "admin_pass",
            passwordencoder.encode("kahano1988"),
            Role.ADMIN.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            ),
            new AppUser(UUID.randomUUID(),
            "user_pass",
            passwordencoder.encode("kahano243"),
            Role.CUSTOMER.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            )
        
         );

         return appUserList;
        
    }
    

    @Override
    public Optional<AppUser> SelectAppUserByName(String username){
        
        return get_AppUsers()
            .stream()
            .filter(appUser -> username.equals(appUser.getUsername()))
            .findFirst();
    }


    @Override
    public Boolean existsByUsername(String username) {
        return get_AppUsers()
                .stream()
                .anyMatch(appuser -> username.equals(appuser.getUsername()));
    }

    
}
