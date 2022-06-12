package com.ensa.gi4.modele;

public class MaterielAllocated {
	private int id;

    private int userID;

    private int materielID;
   
    private String nameMaterial;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMaterielID() {
        return materielID;
    }

    public void setMaterielID(int materielID) {
        this.materielID = materielID;
    }

    public String getNameMaterial() {
    	return nameMaterial;
    }
    public void setNameMaterial(String name) {
	this.nameMaterial=name;
}

    @Override
    public String toString() {
        return " \nuserID : " + userID + " \n" + "materielID : " + materielID + " \n" + "Nom du materiel : " + nameMaterial + " \n"  ;
               
    }
}
