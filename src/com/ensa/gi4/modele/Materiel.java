package com.ensa.gi4.modele;

public abstract class Materiel {
    private int id;

    private String code;

    private String name;

    private boolean availability;

    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return " \nNom : " + name + " \n" + "Code : " + code + " \n" + "Disponibilité : " + availability + " \n" + "Quantité : " +  quantity
                + "\n----------------------------\n";
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
