package com.ensa.gi4.modele;

import java.sql.Date;

public abstract class Allocation {
	
	private int id;
	private String userName;
	private String role;
	private String materielName;
	private String type;
	private Date dateDebut;
	private Date dateFin;
	
	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getRole() {
		return role;
	}
	public String getMaterielName() {
		return materielName;
	}
	public String getType() {
		return type;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
    public String toString() {
        return "username = " + userName + ", role = " + role + ", nom materiel = " + materielName + ", type = " + type + ", date debut = " + dateDebut + ", date fin = " + dateFin;
    }
}
