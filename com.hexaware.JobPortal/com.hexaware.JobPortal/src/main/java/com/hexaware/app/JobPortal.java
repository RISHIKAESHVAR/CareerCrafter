package com.hexaware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hexaware")
public class JobPortal {

	public static void main(String[] args) {
		SpringApplication.run(JobPortal.class, args);
		System.out.println("welcomwe");
	}

}
