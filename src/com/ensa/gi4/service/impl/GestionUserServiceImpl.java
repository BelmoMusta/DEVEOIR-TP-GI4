package com.ensa.gi4.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;

@Component
public class GestionUserServiceImpl implements GestionUserService {

	UserDao userDao;
	
    @Autowired
	public GestionUserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}


	@Override
	public User Connexion(String nom, String passwd) {
		return userDao.Auth(nom, passwd);
	}
	
	@Override
	public String Hashing(String passwd) {
		String generatedPassword = null;
		
	    try{
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(passwd.getBytes());
	      byte[] bytes = md.digest();
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }
	      generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
		return generatedPassword;
	}

}
