package com.ensa.modele;

public class Livre extends Materiel {

private String auteur;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getNom() {
        return super.getName();
    }

    public void setNom(String nom) {
        super.setName(nom);
    }
    public String getCode() {
        return super.getCode();
    }

    public void setCode(String code) {
        super.setCode(code);
    }
}
