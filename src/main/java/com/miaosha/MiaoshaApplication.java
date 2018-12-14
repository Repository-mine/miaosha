package com.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.miaosha")
@MapperScan(basePackages = "com.miaosha.mapper")
public class MiaoshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiaoshaApplication.class, args);
	}

}

