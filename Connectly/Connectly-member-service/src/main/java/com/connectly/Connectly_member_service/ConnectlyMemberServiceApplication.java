package com.connectly.Connectly_member_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConnectlyMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectlyMemberServiceApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "hrithik@0216";
		String encodedPassword = "$2a$10$2usvHvEkH2cD/cAYcNUUy.7yNxG12SeGdrOcSAIsL/926eiN8Xtj2"; // From DB

		System.out.println("Passwords match? " + encoder.matches(rawPassword, encodedPassword));
	}

}
