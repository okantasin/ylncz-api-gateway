package com.ylncz.apgateway.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

public class JwtTokenGenerator {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret; // Yaml'den gelen JWT Secret


    public static void main(String[] args) {
        // Secret key
        String secretKey = "f4c7aab9bb2cfa2f4ef89d6d94c671c2427e926102c44b1407d3f79a7f75bec";
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // Token oluşturma
        String token = Jwts.builder()
                .setSubject("okantasin") // Token'a kullanıcı adı ekle
                .claim("role", "USER") // Ekstra bilgiler ekle (örneğin, rol)
                .setIssuedAt(new Date()) // Token'ın oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 saat geçerli
                .signWith(key, SignatureAlgorithm.HS256) // Secret key ile imzalama
                .compact();

        System.out.println("Generated Token: " + token);
    }
}