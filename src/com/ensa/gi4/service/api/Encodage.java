package com.ensa.gi4.service.api;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encodage {

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
