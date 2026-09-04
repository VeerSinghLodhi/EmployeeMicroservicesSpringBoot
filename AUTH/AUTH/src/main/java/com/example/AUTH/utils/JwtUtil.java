package com.example.AUTH.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.awt.*;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "LSDKJFIOEARFJKAJFQ983WEIUOJFEIW8493IEROJFRU89EIJRU8H8RY43Q8IUERH78Q4WEURYQ3478EW49REI894RE";

    public String generateToken(String username) {

        Map<String,Object> claims=new HashMap<>();
        claims.put("email","veerlodhi54@gmail.com");


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .signWith(getKey())
                .claims(claims)
                .compact();
    }

    public Key getKey(){
        byte [] bytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public Claims getClaims(String token){
        return Jwts.parser().verifyWith((SecretKey)getKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public Date extractExpiration(String token){
        return getClaims(token).getExpiration();
    }
}
