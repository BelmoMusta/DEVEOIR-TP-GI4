package com.ensa.gi4.modele;

public class MaterielAllocated {

    private int id;

    private int userID;

    private int materielID;

    private int quantity;

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

    @Override
    public String toString() {
        return " \nuserID : " + userID + " \n" + "materielID : " + materielID + " \n" + "Quantité : " +  quantity
                + "\n----------------------------\n";
    }
}

