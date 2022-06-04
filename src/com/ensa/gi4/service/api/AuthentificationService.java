package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.User;

public interface AuthentificationService {

	User login (List<String> userData); 
}
