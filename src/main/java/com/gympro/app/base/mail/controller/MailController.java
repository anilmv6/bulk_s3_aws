package com.gympro.app.base.mail.controller;


import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gympro.app.base.mail.service.EmailServiceImpl;
import com.gympro.app.organization.domain.MailObject;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    public EmailServiceImpl emailService;

   // @Value("${attachment.invoice}")
    private String attachmentPath;

	/*
	 * @Autowired public SimpleMailMessage template;
	 */

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public  ResponseEntity<Void> createMail(MailObject mailObject) {
       
        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/sendTemplate", method = RequestMethod.POST)
    public  ResponseEntity<Void> createMailWithTemplate(MailObject mailObject) throws MessagingException, IOException {
       
        emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
                mailObject.getSubject(),
                new SimpleMailMessage(),
                mailObject.getText());

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/sendAttachment", method = RequestMethod.POST)
    public  ResponseEntity<Void> createMailWithAttachment(MailObject mailObject) {
      
        emailService.sendMessageWithAttachment(
                mailObject.getTo(),
                mailObject.getSubject(),
                mailObject.getText(),
                attachmentPath
        );

        return new ResponseEntity(HttpStatus.OK);
    }
}
