package com.example.Practice.Fundamentals.processes.emailSender.serviceInterface;

import com.example.Practice.Fundamentals.processes.emailSender.model.EmailDetails;

public interface EmailServiceInterface {
    void sendSimpleMail(EmailDetails details);
    void sendSimpleMail2(EmailDetails details);
}
