package io.spring.microservices.javarestclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JavaRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaRestClientApplication.class, args);
	}

}

