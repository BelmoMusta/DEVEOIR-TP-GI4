package com.ensa.gi4.datatabase.api;


import org.springframework.stereotype.Repository;

import com.ensa.gi4.modele.Utilisateur;

@Repository
public interface UserDao {
	Utilisateur findUser(String username, String password);

}
