package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
	User Connexion(String nom, String passwd);
	String Hashing(String passwd);
}
