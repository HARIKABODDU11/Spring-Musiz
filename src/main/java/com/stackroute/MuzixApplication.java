package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //to track and log every change
public class MuzixApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzixApplication.class, args);
	}

}
