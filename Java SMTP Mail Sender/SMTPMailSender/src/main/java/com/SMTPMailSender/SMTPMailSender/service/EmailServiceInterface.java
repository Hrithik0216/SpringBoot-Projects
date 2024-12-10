package com.SMTPMailSender.SMTPMailSender.service;

import com.SMTPMailSender.SMTPMailSender.model.EmailDetails;

public interface EmailServiceInterface {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
