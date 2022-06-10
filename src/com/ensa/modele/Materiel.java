package com.ensa.modele;

public abstract class Materiel {
    private String name;
    private Float prix;

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){return "Nom : "+this.getName()+" Code : "+this.getCode()+" Prix : "+this.getPrix();}
}
