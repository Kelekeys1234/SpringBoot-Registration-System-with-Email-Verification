package com.email.springEmail.emailSending;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSending implements EmailSender{
	private final Logger logger =  LoggerFactory.getLogger(EmailSending.class);
	
	private final JavaMailSender mailSender ;
	
	public EmailSending(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	@Async
	public void emailSending(String to, String email) throws Exception  {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , "utf-8");
		try {
			helper.setText(email , true);
			helper.setTo(to);
			helper.setSubject("please confirm your email");
			helper.setFrom("fkelenna@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("mail not send " ,e);
			throw new Exception("email not send");
		}
		
		
	}
	
	public Logger getLogger() {
		return logger;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}
	
	

}
