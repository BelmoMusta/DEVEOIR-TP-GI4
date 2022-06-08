package com.ensa.gi4.modele;

public abstract class Materiel {
	private Long id;
	private Long idUtilisateur;
    private String code;
    private String name;
    private String dure;
    private boolean alloue;
    private boolean disponible;
    private String usernameUtilisateur;


	public String getUsernameUtilisateur() {
		return usernameUtilisateur;
	}

	public void setUsernameUtilisateur(String usernameUtilisateur) {
		this.usernameUtilisateur = usernameUtilisateur;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getName() {
        return name;
    }

    public boolean isAlloue() {
		return alloue;
	}

	public void setAlloue(boolean alloue) {
		this.alloue = alloue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getDure() {
		return dure;
	}

	public void setDure(String dure) {
		this.dure = dure;
	}


	public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

	@Override
	public String toString() {
		return "Materiel [id=" + id + ", idUtilisateur=" + idUtilisateur + ", usernameUtilisateur=" + usernameUtilisateur + ", code=" + code + ", name=" + name
				+ ", dure=" + dure + ", alloue=" + alloue + ", disponible=" + disponible +  "]";
	}

	
    
    
}
