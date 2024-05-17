//package com.au.GenesianTheatreCompany.ServieceImplTest;
//
//import com.au.GenesianTheatreCompany.service.impl.EmailServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.mail.MailSendException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class EmailServiceImplTest {
//
//    private JavaMailSender mailSender = mock(JavaMailSender.class);
//    private EmailServiceImpl emailService = new EmailServiceImpl(); // Assuming constructor injection
//
//    @Test
//    void sendEmail_Failure() {
//        try {
//            String recipient = "genesian42@outlook.com";
//            String subject = "Test Subject";
//            String content = "Hello, this is a test email.";
//            System.out.println("mailSender: " + mailSender); // Should not be null
//            doThrow(new MailSendException("Simulated mail failure")).when(mailSender).send(any(SimpleMailMessage.class));
//            emailService.sendEmail(recipient, subject, content);
//            fail("MailSendException expected but not thrown");
//        } catch (MailSendException e) {
//            System.out.println("MailSendException caught as expected");
//        } catch (Exception e) {
//            e.printStackTrace(); // To see any other exceptions like NullPointerException
//            fail("Unexpected exception type thrown");
//        }
//    }
//
//}
