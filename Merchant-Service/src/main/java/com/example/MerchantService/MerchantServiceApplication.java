package com.example.MerchantService;

import com.example.MerchantService.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class MerchantServiceApplication {


	@Bean
	public UniqueStringGenerator getUniqueStringGenerator(){
		return new UniqueStringGenerator();
	}

	@Bean
	public SMSConfig getSMSConfig(){
		return new SMSConfig();
	}

	@Bean
	public WhatsAppCommunication getWhatsAppCommunication(){
		return new WhatsAppCommunication();
	}

	@Bean
	public DummyDataGen getDummyDataGen(){
		return new DummyDataGen();
	}

	@Bean
	public DateGen getDateGen(){
		return new DateGen();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MerchantServiceApplication.class, args);
	}

}
