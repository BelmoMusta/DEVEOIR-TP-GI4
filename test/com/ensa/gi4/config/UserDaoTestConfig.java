package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;

@Configuration
public class UserDaoTestConfig {
	
	@Bean
	public UserDao userDaoImpl() {
		return new UserDaoImpl(); 
	}

}
