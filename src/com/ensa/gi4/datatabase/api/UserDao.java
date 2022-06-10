package com.ensa.gi4.datatabase.api;

import java.util.List;
import java.util.Optional;

import com.ensa.gi4.modele.User;

public interface UserDao {
	Optional<User> login(List<String> userData);
	Optional<List<User>> findAll(); 
}
