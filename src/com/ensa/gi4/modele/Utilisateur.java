package com.ensa.gi4.modele;



public class Utilisateur {
	private int userId;
	private String userName;    
    private String password;
    private String role;
    
    
	public Utilisateur() {
	}

    
	public Utilisateur(String username, String password) {
		this.userName=username;
		this.password=password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getRole() {
		return role;
	}
	public boolean isAdmin() {
		return "admin".equals(role);
		
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
    public String toString() {
		   return "name= "+userName+", password= "+password+", role= "+role+"\n";
    }
    
}
