package com.ensa.gi4.modele;

public  class Materiel {
    private int id ;
    private String type;
    private String name;
    private int isAllocated ;
    private int stock ;
    private Boolean available ;

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Materiel() {
    }

    public Materiel(String name , String type) {
        this.type = type;
        this.name = name;
    }

    public int getAllocated() {
        return isAllocated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAllocated(int allocated) {
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
