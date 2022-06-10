package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.impl.GestionChaiseServiceImpl;
import com.ensa.gi4.service.impl.GestionLivreServiceImpl;

@Configuration
public class MaterielServiceTestConfig {

	@Bean 
	public MaterielDao materielDaoImpl() {
		return new MaterielDaoImpl(); 
	}
	
	@Bean
	public GestionMaterielService getLivreService() {
		return new GestionLivreServiceImpl(); 
	}
	
	@Bean
	public GestionMaterielService getChaiseService() {
		return new GestionChaiseServiceImpl(); 
	}
	
	
	@Bean
	public ApplicationPublisher applicationPublisher() {
		return new ApplicationPublisher(); 
	}

}
