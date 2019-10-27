package com.luanvan.service;

public interface EmailService {

	void sendHTML(String to, String subject, String body);
}
