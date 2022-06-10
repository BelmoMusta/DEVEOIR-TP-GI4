package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;

@Configuration
public class MaterielDaoTestConfig {

	@Bean
	public MaterielDao materielDaoImpl() {
		return new MaterielDaoImpl(); 
	}
}
