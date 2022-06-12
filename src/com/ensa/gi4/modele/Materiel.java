package com.ensa.gi4.modele;

public abstract class Materiel {
	private int idMateriel;
    private String code;
    private String name;
	private String type;
	private boolean isFree;
	private boolean isAllocate;

    public String getName() {
        return name;
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


	public boolean isAllocate() {
		return isAllocate;
	}

	public void setAllocate(boolean isAllocate) {
		this.isAllocate = isAllocate;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdMateriel() {
		return idMateriel;
	}

	public void setIdMateriel(int idMateriel) {
		this.idMateriel = idMateriel;
	}
	@Override
    public String toString() {
        return  "idMateriel=" + idMateriel + ", " + 
        		"name = " + name + ", " + 
        		"code = " + code  + ", " +
    			"typeMaterial = " + type + ", " + 
        		"estDisponible = " + isFree + ", " +
        		"estAlloué = " + isAllocate ;    
    }
}
