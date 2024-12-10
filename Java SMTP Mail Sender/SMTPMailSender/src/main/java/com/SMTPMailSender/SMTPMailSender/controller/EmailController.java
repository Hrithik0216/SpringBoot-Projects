package com.SMTPMailSender.SMTPMailSender.controller;

import com.SMTPMailSender.SMTPMailSender.model.EmailDetails;
import com.SMTPMailSender.SMTPMailSender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    EmailService emailService;

    @ResponseBody
    @RequestMapping(value="/sendSimpleMail", method= RequestMethod.POST)
    public String sendSimpleMail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details);
    }
    @ResponseBody
    @RequestMapping(value="/sendMailWithAttachment", method= RequestMethod.POST)
    public String sendMailWithAttachment(@RequestBody EmailDetails details){
        return emailService.sendMailWithAttachment(details);
    }
}
