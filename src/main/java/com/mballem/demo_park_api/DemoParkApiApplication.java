package com.mballem.demo_park_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.mballem.demo_park_api")
@SpringBootApplication
public class DemoParkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoParkApiApplication.class, args);
	}

}
