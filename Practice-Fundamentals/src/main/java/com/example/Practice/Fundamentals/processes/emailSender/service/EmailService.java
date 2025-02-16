package com.example.Practice.Fundamentals.processes.emailSender.service;

import com.example.Practice.Fundamentals.processes.emailSender.model.EmailDetails;
import com.example.Practice.Fundamentals.processes.emailSender.serviceInterface.EmailServiceInterface;
import com.example.Practice.Fundamentals.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailServiceInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    @Async
    public void sendSimpleMail(EmailDetails details) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setSubject(details.getSubject());
            message.setText(details.getBody());
            sleep(2000);
            mailSender.send(message);
            LOGGER.info("Email sent successfully to "+details.getTo()+" "+Thread.currentThread().getName());
        }catch(Exception e){
            LOGGER.error("Error in sending mail to "+details.getTo()+" "+e.getMessage());
        }
    }

    @Override
    @Async
    public void sendSimpleMail2(EmailDetails details) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setSubject(details.getSubject());
            message.setText(details.getBody());
            sleep(2000);
            mailSender.send(message);
            LOGGER.info("Email sent successfully to "+details.getTo()+" "+Thread.currentThread().getName());
        }catch(Exception e){
            LOGGER.error("Error in sending mail to "+details.getTo()+" "+e.getMessage());
        }
    }

    private void sleep(int ms) {
        try{
            Thread.sleep(ms);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
}
