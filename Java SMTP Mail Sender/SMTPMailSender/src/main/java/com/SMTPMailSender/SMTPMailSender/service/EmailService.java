package com.SMTPMailSender.SMTPMailSender.service;

import com.SMTPMailSender.SMTPMailSender.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Service
public class EmailService implements EmailServiceInterface{
    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setSubject(details.getSubject());
            message.setText(details.getBody());
            mailSender.send(message);
            return "Email sent successfully";
        }catch(Exception e){
            return "Email sent failed";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try{
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(details.getFrom());
            helper.setTo(details.getTo());
            helper.setSubject(details.getSubject());
            helper.setText(details.getBody());
            FileSystemResource fileSystemSource = new FileSystemResource(new File(details.getAttachment()));
            helper.addAttachment(details.getAttachment(), fileSystemSource);
            mailSender.send(message);
            return "Email sent successfully";
        }catch (MessagingException e){
            return "Email sent failed";
        }
    }
}
