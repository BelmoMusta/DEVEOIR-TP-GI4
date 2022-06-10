package com.ensa.gi4.modele;

public class Utilisateur {

	//Les attributs
	private Long idUser;
	private String username;
	private String password;
	private String role;
	
	//Le constructeur générique
	public Utilisateur(Long idUser, String username, String password, String role) {
		
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	//Le constructeur par défaut
	public Utilisateur() {
		
	}

	//Les getteurs et les setteurs
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}
