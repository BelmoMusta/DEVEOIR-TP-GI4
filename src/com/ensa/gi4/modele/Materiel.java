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

    @Override
    public String toString() {
        String available = "";
        if (availability) {
            available = "disponible";
        } else {
            available = "indisponible";
        }
        return "\nMateriel : " + name + " / " +
                "Code : " + code + " / " +
                "Disponibilité : " + available + " / " +
                "Quantité : " + quantity + ".";
    }

}
