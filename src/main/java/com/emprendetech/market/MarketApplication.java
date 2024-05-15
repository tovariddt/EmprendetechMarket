	package com.emprendetech.market;


import java.time.Duration;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;	

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class MarketApplication {
	
	
	@PostConstruct
    public void init(){
		//Setting Spring Boot SetTimeZone
		final String defaultTimeZone = "America/Mexico_City";
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
	
    }
	



	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}

	
	@Bean	
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(60000))
                .setReadTimeout(Duration.ofMillis(60000))
				.build();
	}
	
	


}
