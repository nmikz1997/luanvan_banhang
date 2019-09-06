package com.luanvan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableJpaAuditing
@SpringBootApplication
public class LuanVanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuanVanApplication.class, args);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
