package com.ensa.gi4.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;


@Service
public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	UserDao userDao; 
	
	@Override
	public User login(List<String> userData) {
		return userDao.login(userData); 
	}

	

}
