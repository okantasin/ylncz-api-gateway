package com.ylncz.apgateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenUtil {

    private final String jwtSecret;

    public JwtTokenUtil(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public void validateToken(String token) {
        Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}