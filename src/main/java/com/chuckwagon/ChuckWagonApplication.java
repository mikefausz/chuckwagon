package com.chuckwagon;

import com.chuckwagon.services.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChuckWagonApplication {


	public static void main(String[] args) {
		SpringApplication.run(ChuckWagonApplication.class, args);
	}
}
