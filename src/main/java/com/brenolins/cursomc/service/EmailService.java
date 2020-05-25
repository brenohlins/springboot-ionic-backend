package com.brenolins.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.brenolins.cursomc.domain.Pedido;

public interface EmailService {

	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
	
	
	
}
