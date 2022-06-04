package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.User;

public interface UserDao {
	User login(List<String> userData); 
}
