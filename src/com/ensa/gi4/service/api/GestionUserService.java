package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface GestionUserService {
	User findUser(String name,String password);
	 boolean userAdmin(User user);
	 void listerUsers();
	
  

}
