package com.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

//@EnableAutoConfiguration
//@RestController
public class App {
	public static void main(String[] args) {
		System.out.println("Hello world");
		SpringApplication.run(App.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Hello world";
	}
}
