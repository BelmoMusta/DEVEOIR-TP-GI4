package com.ensa.gi4.modele;

public abstract class Materiel {
    private int id ;
    private String type;
    private String name;
    private Boolean isAllocated ;

    public Boolean getAllocated() {
        return isAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAllocated(Boolean allocated) {
        isAllocated = allocated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "name = " + name + ", type = " + type;
    }
}
