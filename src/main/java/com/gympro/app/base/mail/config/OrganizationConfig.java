package com.gympro.app.base.mail.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrganizationConfig {

	
	
    /*
     * @Bean public JavaMailSender getJavaMailSender() { JavaMailSenderImpl
     * mailSender = new JavaMailSenderImpl();
     * mailSender.setHost("smtpout.asia.secureserver.net"); //
     * mailSender.setPort(587); mailSender.setUsername("admin@gymprosoftware.com");
     * mailSender.setPassword("Test@123");
     * 
     * Properties props = mailSender.getJavaMailProperties();
     * props.put("mail.transport.protocol", "smtp");
     * props.put("mail.smtp.auth","true"); props.put("mail.smtp.starttls.enable",
     * "true"); props.put("mail.debug", "true"); props.put("mail.from",
     * "admin@gymprosoftware.com"); props.put("mail.smtp.port", "587");
     * props.put("mail.smtp.host", "smtpout.asia.secureserver.net");
     * 
     * return mailSender; }
     */
    @Bean public Message getJavaMailSender() throws AddressException, MessagingException {
    Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtpout.asia.secureserver.net");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication("admin@gymprosoftware.com", "Test@123");
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress("admin@gymprosoftware.com", false));
	   
	   return msg;
	   
    }   
	 
}
