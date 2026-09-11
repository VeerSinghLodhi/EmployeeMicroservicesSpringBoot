package com.example.API_GATEWAY.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "LSDKJFIOEARFJKAJFQ983WEIUOJFEIW8493IEROJFRU89EIJRU8H8RY43Q8IUERH78Q4WEURYQ3478EW49REI894RE";


    public Key getKey(){
        byte [] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public void validateToken(String token) {
        Jwts.parser().verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token);
    }

}
