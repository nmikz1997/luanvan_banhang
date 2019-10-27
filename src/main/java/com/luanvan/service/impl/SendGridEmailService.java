package com.luanvan.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.service.EmailService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

@Service
public class SendGridEmailService implements EmailService {
	
	private SendGrid sendGridClient;
	private static final String fromWebsite = "trinhthenguyen123@gmail.com";
    
	@Autowired
    public SendGridEmailService(SendGrid sendGridClient) {
        this.sendGridClient = sendGridClient;
    }
	
	@Override
    public void sendHTML(String to, String subject, String body) {
        sendEmail(fromWebsite, to, subject, new Content("text/html", body));
    }
	
	private void sendEmail(String from, String to, String subject, Content content) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        mail.setReplyTo(new Email("trinhthenguyen123@gmail.com"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGridClient.api(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
