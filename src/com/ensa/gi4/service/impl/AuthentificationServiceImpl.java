package com.ensa.gi4.service.impl;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AuthentificationService;


@Service
public class AuthentificationServiceImpl implements AuthentificationService {

	@Autowired
	@Qualifier("userDaoImpl")
	UserDao userDao; 
	@Value("${string.authentificationService.addUser.Failed}")
	private String echecCreation; 
	
	@Override
	public Optional<User> login(List<String> userData) {
		return userDao.login(userData); 
	}

	@Override
	public Optional<User> signUp(List<String> userData) {
		
		int test = userDao.signUp(userData);
		if (test == 1) {
			return userDao.login(userData); 
		}else {
			System.out.println(echecCreation);
		 	return	Optional.empty(); 
		}
	}

	

}
