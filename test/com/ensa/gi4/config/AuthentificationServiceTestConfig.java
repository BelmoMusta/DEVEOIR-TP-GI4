package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.service.api.AuthentificationService;
import com.ensa.gi4.service.impl.AuthentificationServiceImpl;

@Configuration
public class AuthentificationServiceTestConfig {
	
	@Bean
	public UserDao userDaoImpl() {
		return new UserDaoImpl(); 
	}
	
	@Bean
	public AuthentificationService getAuthentificationService() {
		return new AuthentificationServiceImpl(); 
	}

}
