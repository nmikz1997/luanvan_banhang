package com.luanvan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class WebMVCConfig {
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	@Value("${sendgrid.api.key}") String sendGridAPIKey;
	
	@Bean
	public SendGrid SendGridMail() {
		return new SendGrid(sendGridAPIKey);
	}
}
