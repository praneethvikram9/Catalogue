package com.example.catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	 private JavaMailSender javaMailSender;

	@Override
	public void sendMail(String toEmail, String subject, String message) {
        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("no-reply-orderConfirmation@example.com");

        javaMailSender.send(mailMessage);
		
	}



}
