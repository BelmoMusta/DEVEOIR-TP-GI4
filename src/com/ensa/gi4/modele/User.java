package com.ensa.gi4.modele;

public abstract class User {
	  private String name;
	    private String password;
	    private String role;
	    private long id;
	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
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

	    public void setRole(String role) {
	        this.role = role;
	    }

	    @Override
	    public String toString() {
	        return "name = " + name+", id="+id +"password="+password;
	    }

}
