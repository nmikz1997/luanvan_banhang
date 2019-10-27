package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.luanvan.service.EmailService;

@RestController
public class SendMailTest {
	
	private EmailService emailService;
	
	@Autowired
	public SendMailTest(EmailService emailService) {
		this.emailService = emailService;
	}
	
	
//	@GetMapping("test-send-mail")
//	public void testSendMail(Long groupId){
//		String mailContent = mailTemplate(groupId);
//		emailService.sendHTML("nguyenb1507129@student.ctu.edu.vn", "Thư gửi kiểm thử lần 2", mailContent);
//	}
	
	
}
