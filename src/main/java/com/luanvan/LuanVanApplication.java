package com.luanvan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class LuanVanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuanVanApplication.class, args);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	
//	@GetMapping(value = "/content1")
//	public String content1() {
//		return "admin/index";
//	}
//	
//	@GetMapping(value = "/content2")
//	public String content2() {
//		return "Content2";
//	}
}
