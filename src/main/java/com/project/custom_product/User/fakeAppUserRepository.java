package com.project.custom_product.User;

import java.util.Optional;



public interface fakeAppUserRepository {
    
    Optional<AppUser> SelectAppUserByName(String name);
    Boolean existsByUsername(String username);
}
