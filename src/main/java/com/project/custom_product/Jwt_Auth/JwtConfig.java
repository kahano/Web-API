package com.project.custom_product.Jwt_Auth;

import java.sql.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

@Component
public class JwtConfig {

    public static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public String generate_jwtToken(Authentication authentication){

        String username = authentication.getName();
        // Date currentdate = new Date(0);
        // Date expiration_date = new Date(currentdate.getTime() + JwtConstants.TOKEN_EXPIRATION); 
		


        String token = Jwts.builder()
        .subject(username)
        .claim("authorities", authentication.getAuthorities())
        .signWith(key)
        .compact();

        System.out.println("New token:");
        System.out.println(token);

        return token;

       
                            
    }

    public String getUsernameFromJWT(String token){
		Claims claims = Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
			return true;
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}
	}



    
}
