package com.project.custom_product.Auth;

import java.util.Optional;



public interface AppUserRepository  {

    Optional<AppUser> findByUsername(String name);
    
    


    
    
}
