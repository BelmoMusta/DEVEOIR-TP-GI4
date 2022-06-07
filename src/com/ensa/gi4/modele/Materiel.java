package com.ensa.gi4.modele;

public abstract class Materiel {
	private int id;
	private String code;
    private String name;   
    private boolean disponible;
    private int allouer;
    private String duree;
    
    public int getAllouer() {
		return allouer;
	}

	public void setAllouer(int allouer) {
		this.allouer = allouer;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public int getId() {
  		return id;
  	}

  	public void setId(int id) {
  		this.id = id;
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
        return "name = " + name + ", code = " + code+" allouer = "+allouer+" duree = "+ duree+"\n";
    }
}
