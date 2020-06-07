package com.app.unemploymentRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UnemploymentRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnemploymentRateApplication.class, args);
	}

}
