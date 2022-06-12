package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UserDAO;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;
@Component("userService")
public class GestionUserServiceImpl implements GestionUserService {
	 UserDAO user;
	
	 @Autowired
	 public GestionUserServiceImpl(UserDAO user) {
		 this.user = user;
	 }

	

	@Override
	public User login(String name, String password) {
		return   user.login(name,password);
	
	}

	@Override
	public void register(String name, String password) {
		user.register(name,password);
		
	}



	


	


}
