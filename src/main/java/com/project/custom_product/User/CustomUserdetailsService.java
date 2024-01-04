package com.project.custom_product.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("fake")
    private final fakeAppUserRepository user_repository;

  
      
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        AppUser user = user_repository.SelectAppUserByName(username).orElseThrow(()-> new AppUserNotFoundException(username));
         return user;
    }

   

}
