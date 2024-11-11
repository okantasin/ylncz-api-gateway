package com.ylncz.apgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YlnczApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlnczApiGatewayApplication.class, args);
	}

}
