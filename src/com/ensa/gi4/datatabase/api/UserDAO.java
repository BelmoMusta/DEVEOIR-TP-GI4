package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.User;

public interface UserDAO {
	 User login(String name, String password);
	 void register(String name,String password);
	User userExist(String username, String password);
	
}
