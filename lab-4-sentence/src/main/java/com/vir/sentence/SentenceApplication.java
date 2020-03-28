package com.vir.sentence;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class SentenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentenceApplication.class, args);
	}

	//  This "LoadBalanced" RestTemplate 
	//  is automatically hooked into Ribbon:
	@Bean @LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}  
	
	/**
     * Número de hilos que voy a poder ejecutar a la vez
     */
    @Bean
    public Executor executor() {
    	return Executors.newFixedThreadPool(6);
    }
}
