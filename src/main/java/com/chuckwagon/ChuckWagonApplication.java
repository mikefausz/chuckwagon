package com.chuckwagon;

import com.chuckwagon.services.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ChuckWagonApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChuckWagonApplication.class, args);
	}
}
