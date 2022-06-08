package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private long id ;
    private boolean disponible;
    private int allouer;
    private String duree;

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

  	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	 

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

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
	 @Override
	 public String toString() { 
	        return  "  \n id : "+id+"  ********  name : " + name + "  ********  code : " + code +"  ********  disponibilité : "+disponible+"  ********  alloucation : "+allouer+"  ********  duree = "+ duree+" j \n";
	    }
}
