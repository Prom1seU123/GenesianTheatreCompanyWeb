package com.au.GenesianTheatreCompany.service.impl;

import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.admin.email}")
    private String adminEmail;
    public Result sendEmail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(adminEmail);
        message.setTo(adminEmail);
        message.setSubject(subject);
        message.setText("My contact: "+email+"\n"+content);
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            System.err.println("Mail send failed: " + ex.getMessage());
            ex.printStackTrace();
        }
        return Result.suc("Email sent successfully");
    }
}
