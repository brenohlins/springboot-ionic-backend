package com.brenolins.cursomc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;



public class SmtpMailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
	}
}
