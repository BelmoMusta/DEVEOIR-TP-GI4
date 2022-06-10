package com.ensa.gi4.modele;

public abstract class Utilisateur {
	private String username;
	private String Password;
	private String role;
	private Long id_utilisateur;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getId_utilisateur() {
		return id_utilisateur;
	}
	public void setId_utilisateur(Long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	@Override
	public String toString() {
		return "Utilisateur [username=" + username + ", Password=" + Password + ", role=" + role + ", id_utilisateur="
				+ id_utilisateur + "]";
	}
	

}
