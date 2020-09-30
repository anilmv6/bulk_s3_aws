package com.gympro.app.base.mail.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Created by Olga on 7/15/2016.
 */
@Component
public class EmailServiceImpl  {

    @Autowired
   // public JavaMailSender emailSender;
    public Message message;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            //emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               SimpleMailMessage template,
                                               String ...templateArgs) throws MessagingException, IOException {
       // String text = String.format(template.getText(), templateArgs);  
        String userName="Pranavjeet";
        //MimeMessage message = message;
        // pass 'true' to the constructor to create a multipart message
       // MimeMessageHelper helper = new MimeMessageHelper(message, true);
       // helper.setTo(to);
        //helper.setRecipients(Message.RecipientType.TO, InternetAddress.parse("anilhv0722@gmail.com"));
        //helper.setCc("anilmv6@gmail.com");
      //  helper.setText(text);
        
       // helper.setSubject("Welcome to GYM-PRO");
       // helper.setSentDate(new Date());
        
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Welcome to GYM-PRO");
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("anilmv6@gmail.com"));
        message.setContent("We are happy to welcoming you........!!!!", "text/html; charset=utf-8");
        message.setSentDate(new Date());
        
        MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent("Hello "+userName+ ",<br> We are happy to welcoming you........!!!! <br>","text/html; charset=utf-8");

	
	  Multipart multipart = new MimeMultipart();
	  multipart.addBodyPart(messageBodyPart);
	  
	
	  MimeBodyPart attachPart = new MimeBodyPart();
	  
	  attachPart.attachFile("src/main/resources/Gympro_template.html");
	  attachPart.setDisposition(Part.INLINE); multipart.addBodyPart(attachPart);
	 
	  message.setContent(multipart);
	 
           Transport.send(message);
    }

    public void sendMessageWithAttachment(String to,
                                          String subject,
                                          String text,
                                          String pathToAttachment) {
        try {
            MimeMessage message = null;//emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setCc("anilmv6@gmail.com");
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

           // emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
