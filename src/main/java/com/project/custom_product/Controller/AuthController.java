package com.project.custom_product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.custom_product.DTO.AuthResponseDTO;
import com.project.custom_product.DTO.LoginDTO;

import com.project.custom_product.Jwt_Auth.JwtConfig;


import com.project.custom_product.User.AppUserRepositoryDao;




@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

    private final AuthenticationManager authenticationManager;

    private final AppUserRepositoryDao userRepository;




    private final JwtConfig jwtconfig;

 
    public AuthController( AuthenticationManager authenticationManager,AppUserRepositoryDao userRepository,
                           JwtConfig jwtconfig ){

                                this.authenticationManager = authenticationManager;
                                this.userRepository = userRepository;
                                this.jwtconfig = jwtconfig;

                           }



    @PostMapping("login")
        public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO logindto){
          
            if (userRepository.existsByUsername(logindto.getUsername())) {
                 Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                            logindto.getUsername(),
                            logindto.getPassword()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = jwtconfig.generate_jwtToken(authentication);
                    return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
            }
            else{
                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

           
        

    // @PostMapping("register")
    //      public ResponseEntity<String> register(@RequestBody  RegisterDTO registerDto) {
    //         if (userRepository.existsByUsername(registerDto.getUsername())) {
    //             return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
    //         }

    //         AppUser user = new AppUser();
    //         user.setUsername(registerDto.getUsername());
    //         user.setPassword(passwordencoder.encode((registerDto.getPassword())));
    //         user.setRole(Role.CUSTOMER);
    //         user.setGrantedAuthorities(Role.CUSTOMER.get_GrantedAuthorities());

    //         // Role roles = roleRepository.findByName("USER").get();
    //         // user.setRoles(Collections.singletonList(roles));

    //         // userRepository.save(user);

    //     return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    // }

}
