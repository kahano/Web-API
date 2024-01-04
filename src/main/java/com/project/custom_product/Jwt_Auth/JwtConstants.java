package com.project.custom_product.Jwt_Auth;



public class JwtConstants {
    
    public static final String SECRET_KEY = "MEgCQQCh2ZQg7kUAXzFqYP4wrbeYjgAl0Q69eYfUteelWqauewRWd76NMpiY45AR\r\n" + //
            "T2bOHDhwuOLYqDmBthAXrMa58RJ3AgMBAAE=";

    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 
}
