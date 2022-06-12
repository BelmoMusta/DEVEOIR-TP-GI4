package com.ensa.gi4.modele;

public class Allocations {
    private int id;

    private int userID;

    private int materielID;

    private int availability;

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

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Allocations{" +
                "userID=" + userID +
                ", materielID=" + materielID +
                ", availability=" + availability +
                '}';
    }
}
