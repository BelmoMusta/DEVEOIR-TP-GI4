package com.ensa.gi4.modele;

public class MaterielAllouer {
	
	private Materiel materiel; 
	private User user;
	
	public Materiel getMateriel() {
		return materiel;
	}
	
	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	} 
	
	@Override
	public String toString() {
		return  user.toString() +" => " + materiel.toString();
	}
	
	

}
