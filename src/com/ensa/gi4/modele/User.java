package com.ensa.gi4.modele;

public abstract class User {
	
	private int Id;
	private String Nom;
	private String Passwd;
	private String Role;
	
	public User(String nom, String passwd) {
		Nom = nom;
		Passwd = passwd;
	}

	public User() {}

	public int getId() {
		return Id;
	}

	public String getNom() {
		return Nom;
	}

	public String getPasswd() {
		return Passwd;
	}
	
	public String getRole() {
		return Role;
	}

	public void setId(int id) {
		Id = id;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public void setPasswd(String passwd) {
		Passwd = passwd;
	}	
	
	public void setRole(String role) {
		Role = role;
	}

}
