package com.ensa.gi4.modele;

public abstract class Materiel {
	private Long id;
	private Long idUtilisateur;
    private String code;
    private String name;
    private String dure;

    public String getName() {
        return name;
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
		return "Materiel [id=" + id + ", idUtilisateur=" + idUtilisateur + ", code=" + code + ", name=" + name
				+ ", dure=" + dure + "]";
	}

	
    
    
}
