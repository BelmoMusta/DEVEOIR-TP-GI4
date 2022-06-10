package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private Long id ;
  
    private Long id_user;
    private String username_user;
    private Boolean disponible;
    private Boolean alloue;
    private String duree;
    private String type;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getUsername_user() {
		return username_user;
	}
	public void setUsername_user(String username_user) {
		this.username_user = username_user;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
	public Boolean getAlloue() {
		return alloue;
	}
	public void setAlloue(Boolean alloue) {
		this.alloue = alloue;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Materiel [code=" + code + ", name=" + name + ", id=" + id + ", id_user=" + id_user + ", username_user="
				+ username_user + ", disponible=" + disponible + ", alloue=" + alloue + ", duree=" + duree + ", type="
				+ type + "]";
	}

   
}
