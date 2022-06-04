package com.ensa.gi4.modele;

public class User {
	private String name; 
	private Role role;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		String value = "Name : " + this.name + ", Role : " + this.role; 
		return value; 
	}
	
	
	
}
