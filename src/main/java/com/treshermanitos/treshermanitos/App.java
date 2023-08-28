package com.treshermanitos.treshermanitos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.treshermanitos.treshermanitos.auth.AuthService;
import com.treshermanitos.treshermanitos.auth.RegisterRequest;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/* @Bean
	public CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstName("Admin")
					.lastName("Admin")
					.email("admin@mail.com")
					.password("password")
					.build();
			System.out.println("Admin token: " + service.register(admin).getToken());

			var manager = RegisterRequest.builder()
					.firstName("user")
					.lastName("user")
					.email("manager@mail.com")
					.password("password")
					.build();
			System.out.println("Manager token: " + service.register(manager).getToken());

		};
	} */
}
