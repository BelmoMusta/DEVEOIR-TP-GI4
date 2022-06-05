package com.ensa.gi4.modele;

public abstract class Personne {
	String name;
	String pw;
	String role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	  @Override
	    public String toString() {
	        return "name = " + name + ", role = " + role;
	    }
	

}
