package com.SMTPMailSender.SMTPMailSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmtpMailSenderApplication {

	public static void main(String[] args) {

		SpringApplication.run(SmtpMailSenderApplication.class, args);
		System.out.println("Mail Sender Application Started");
	}

}
