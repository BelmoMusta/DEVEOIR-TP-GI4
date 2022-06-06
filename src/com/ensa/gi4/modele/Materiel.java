package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private int quantite;

    public String getName() {
        return name;
    }

    public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
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
		return "Materiel [code=" + code + ", name=" + name + ", quantite=" + quantite + "]";
	}
    
    
}
