package com.luanvan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class WebMVCConfig {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
}
