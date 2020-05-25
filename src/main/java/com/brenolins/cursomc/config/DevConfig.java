package com.brenolins.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.brenolins.cursomc.service.DBService;
import com.brenolins.cursomc.service.EmailService;
import com.brenolins.cursomc.service.SmtpMailService;

@Configuration
@Profile("dev")
public class DevConfig {

	
	@Autowired
	private DBService dbService;
	
	
	
	@Bean
	public boolean InstantiateDatabase() throws ParseException{
		
		
		dbService.InstantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}

}
