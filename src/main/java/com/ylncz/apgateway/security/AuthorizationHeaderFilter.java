package com.ylncz.apgateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret; // Yaml'den gelen JWT Secret


    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return Mono.error(new RuntimeException("Authorization header is missing or invalid"));
            }

            String token = authorizationHeader.substring(7); // "Bearer " kısmını kesiyoruz

            try {
                // JWT doğrulama
                Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();

                // Kullanıcı bilgisi veya diğer veriler claims üzerinden alınabilir
                System.out.println("JWT geçerli, kullanıcı bilgisi: " + claims.getSubject());
            } catch (Exception e) {
                return Mono.error(new RuntimeException("Invalid JWT token"));
            }

            return chain.filter(exchange); // Eğer token geçerliyse filtrelemeye devam et
        };
    }

    public static class Config {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

