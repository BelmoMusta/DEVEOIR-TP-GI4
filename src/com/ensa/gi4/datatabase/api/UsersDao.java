package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Users;

public interface UsersDao {
	List<Users> listUsers();

	boolean verifyAdmin();
}
