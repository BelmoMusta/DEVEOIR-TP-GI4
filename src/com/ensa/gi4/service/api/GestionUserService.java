package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
     void init();
	User connexion(String name, String password);
	 String userRole(String name, String password);
}
