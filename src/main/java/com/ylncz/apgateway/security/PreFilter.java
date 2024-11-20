package com.ylncz.apgateway.security;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class PreFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("PreFilter");
        String requestPath = exchange.getRequest().getURI().getPath();
        logger.info("requestPath: " + requestPath);
        HttpHeaders headers = exchange.getRequest().getHeaders();
        headers.keySet().forEach(key -> {
            logger.info("key: " + key + " value: " + headers.getFirst(key));
        });
        return chain.filter(exchange);
    }
}
