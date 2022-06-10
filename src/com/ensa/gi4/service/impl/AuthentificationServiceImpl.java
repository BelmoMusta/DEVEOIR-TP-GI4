package com.ensa.gi4.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;


@Service
public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	@Qualifier("userDaoImpl")
	UserDao userDao; 
	
	@Override
	public Optional<User> login(List<String> userData) {
		return userDao.login(userData); 
	}

	

}
