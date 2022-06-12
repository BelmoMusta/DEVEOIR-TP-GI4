package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.User;

public interface GestionUserService {
		User login(String name, String password);
	    void register(String name,String password);
	    
	  //  public void rendreMateriel(String name);
	  
	}

