package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

public interface UserDao {

	public List<User> getUsers();

	public User getUser(String userName);
	
	public User getUser( int userId);
	
	public List<Role> getRoles(int userId);

	public List<Materiel> getMateriels(String userName);
}
