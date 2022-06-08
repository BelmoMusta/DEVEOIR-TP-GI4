package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
     void init();
	User connexion(String name, String password);
	 Boolean isAdmin(String name, String password);
	void allouerMateriel(String code, String duree);
	void rendreMateriel(int id);
	void listeMaterielAlloue(String name);
}
