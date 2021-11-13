package com.example.CustomerService;

import com.example.CustomerService.util.Config;
import com.example.CustomerService.util.Encryption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CustomerServiceApplication {

	@Bean
	public Encryption getEncryption() throws Exception {
		try{
			return new Encryption();
		}
		catch (Exception e){
			throw new Exception("Some error occured while initializing the " +
					"encryption and decryption class");
		}
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
