package com.ensa.gi4.modele;

public class Allocation {

    private int id;

    private int userID;

    private int materielID;

    private int quantity;

    private String username;

    private String materiel;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    @Override
    public String toString() {
        return "\nUtilisateur : " + username + " / " +
                "Materiel : " + materiel + " / " +
                "Quantité : " + quantity + ".";
    }
}

