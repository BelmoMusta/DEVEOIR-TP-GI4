package com.ensa.gi4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionUserService;
@Component
public class GestionUserServiceImpl implements GestionUserService {
	 @Autowired
	    UserDao userDao;
	
    @Override
	public User findUser(String name,String password) {
		return userDao.findUser(name,password);
      
	};
	 @Override
	public boolean userAdmin(User user) {
	if(user.getRole().equals("admin")) return true;
	return false;
	}
	
	    @Override
	    public void listerUsers() {
	        
	        if(userDao.findAll().size()>0)
	        	System.out.println(userDao.findAll());
				else System.out.println("aucun matériel");
	    }
 

}
