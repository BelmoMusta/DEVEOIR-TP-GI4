package com.ensa.gi4.service.api;

import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

public interface GestionUserService {

	public List<User> getUsers();

	public User getUser(String userName);
	
	public User getUser(int userId);

	public List<Role> getUseRoles(int id);

	public List<Materiel> getMateriels(String userName);
}
