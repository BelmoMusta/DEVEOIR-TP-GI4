package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("gestionUser")
public class GestionUserServiceImpl implements GestionUserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public void init() {
		
		System.out.println("initialisation du service user");
		
	}

	@Override
	public User connexion(String name, String password) {
		
		User user= userDao.findOneUser(name, password);
		String role = userDao.getRole(name);
		System.out.println(role);
		
		if(user!= null){
			
			System.out.println("Vous êtes connecté(e)!");
			return user;
			
			
		}
		else if(role == "admin"){
			System.out.println("vous etes admin");
			return user;
		}
		else {
			System.out.print("Votre nom ou mot de passe est erroné");
			return null;
		}
	
		
	}

	@Override
	public String userRole(String name, String password) {
		return null;
	}

}
