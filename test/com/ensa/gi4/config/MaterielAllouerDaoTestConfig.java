package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.datatabase.impl.MaterielAllouerDaoImpl;

@Configuration
public class MaterielAllouerDaoTestConfig {

	@Bean
	public MaterielAllouerDao materielAllouerDaoImpl() {
		return new MaterielAllouerDaoImpl(); 
	}
}
