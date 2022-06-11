package com.ensa.gi4.modele;

import java.util.List;

public class User {

	private int id;
	private String userName;
	private String password;
	private List<Role> roles;
	private List<Materiel> materiels;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Materiel> getMateriels() {
		return materiels;
	}

	public void setMateriels(List<Materiel> materiels) {
		this.materiels = materiels;
	}

}
