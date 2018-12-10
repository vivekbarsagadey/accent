package com.whizit.accent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccentServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AccentServiceApiApplication.class);
		application.setAdditionalProfiles("ssl");
		application.run(args);
	}
}
