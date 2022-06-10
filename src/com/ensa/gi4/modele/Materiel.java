package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private String etat;
    
    public Materiel() {
    	
    	
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }
}
