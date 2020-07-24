package com.springbootcamp.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String email,String appUrl,String subject,String token){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@domain.com");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm/buyer?token=" +token +"\n Please continue with this" +
                " Activation link and confirm your registration");
        // Send mail
        mailSender.send(mailMessage);
    }

    @Async
    public void sendEmail(String email,String appUrl,String subject,String token,String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@domain.com");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText("To reset your password, please click the link below:\n"
                + appUrl + text +token );
        // Send mail
        mailSender.send(mailMessage);
    }

    @Async
    public void sendEmail(String email,String subject,String text){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@domain.com");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        // Send mail
        mailSender.send(mailMessage);

    }


}
