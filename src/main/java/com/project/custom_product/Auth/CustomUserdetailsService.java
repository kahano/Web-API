package com.project.custom_product.Auth;

import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.custom_product.exception.AppUserNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserdetailsService implements UserDetailsService {

    @Autowired
    private final AppUserRepository user_repository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        AppUser user = user_repository.findByUsername(username).orElseThrow(()-> new AppUserNotFoundException(username));
         return user;
    }

    private List<AppUser> get_AppUsers(){
       
        List<AppUser> appUserList = List.of(

            new AppUser("user_pass",
            bCryptPasswordEncoder.encode("kahano243"),
            Role.CUSTOMER.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            ),

            new AppUser("admin_pass",
            bCryptPasswordEncoder.encode("kahano1988"),
            Role.ADMIN.get_GrantedAuthorities(), 
            true, 
            true,
             true, 
             true
            
            
            )
        
         );

         return appUserList;
        
    }
    


    public Optional<AppUser> findByUsername(String username){
        
        return get_AppUsers()
            .stream()
            .filter(appUser -> username.equals(appUser.getUsername()))
            .findFirst();
    }

}
