package com.ensa.gi4.service.api;

import java.util.List;
import java.util.Optional;

import com.ensa.gi4.modele.User;

public interface AuthentificationService {

	Optional<User> login (List<String> userData); 
}
