package com.ensa.gi4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.datatabase.impl.MaterielAllouerDaoImpl;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.datatabase.impl.UserDaoImpl;
import com.ensa.gi4.service.api.AllocationMaterielService;
import com.ensa.gi4.service.impl.AllocationMaterielServiceImpl;

@Configuration
public class AllocationMaterielServiceTestConfig {
	
	@Bean 
	public MaterielDao materielDaoImpl() {
		return new MaterielDaoImpl(); 
	}
	
	@Bean
	public AllocationMaterielService getAllocationMaterielService() {
		return new AllocationMaterielServiceImpl(); 
	}
	
	@Bean
	public MaterielAllouerDao materielAllouerDaoImpl() {
		return new  MaterielAllouerDaoImpl(); 
	}
	
	@Bean
	public UserDao userDaoImpl() {
		return new UserDaoImpl(); 
	}
}
