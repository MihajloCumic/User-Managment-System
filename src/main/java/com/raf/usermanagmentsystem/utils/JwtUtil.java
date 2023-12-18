package com.raf.usermanagmentsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "My jwt secret key";
    private final int EXPIRATION = 1000 * 60 * 60 * 10;

    public Claims extracAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(this.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token){
        return extracAllClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return extracAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String email, List<String> privileges){
        Map<String, Object> claims = new HashMap<>();
        if(!privileges.isEmpty()) claims.put("authorization", privileges);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, this.SECRET_KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return (userDetails.getUsername().equals(extractEmail(token)) && !isTokenExpired(token));
    }
}
