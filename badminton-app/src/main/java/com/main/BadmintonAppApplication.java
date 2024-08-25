package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("[com.main.app,com.main.controller,com.main.]")
//@ComponentScan(basePackages = {"com.main.controller","com.main.service",
//		"com.main.app","com.main.config","com.main.model","com.main.event","com.main.event.listener"})
//@EnableJpaRepositories("com.main.repository")
//@EntityScan("com.main.entity")
@SpringBootApplication
public class BadmintonAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BadmintonAppApplication.class, args);
	}
}
