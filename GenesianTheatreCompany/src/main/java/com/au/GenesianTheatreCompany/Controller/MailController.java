package com.au.GenesianTheatreCompany.Controller;
import com.au.GenesianTheatreCompany.Common.Result;
import com.au.GenesianTheatreCompany.entity.DTO.Email;
import com.au.GenesianTheatreCompany.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-mail")
    public Result sendMail(@RequestBody Email emailformat) {
        return emailService.sendEmail(emailformat.getEmail(), emailformat.getSubject(), emailformat.getContent());
    }
}

