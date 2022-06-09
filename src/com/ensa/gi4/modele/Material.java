package com.ensa.gi4.modele;

public class Material {
    private Long id;
    private String name;
    private MaterialType materialType;
    private int timeRented;
    private boolean isAvailable;
    private int stock;

    public Material() {
        this.timeRented = 0;
        this.materialType = new MaterialType();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public int getTimeRented() {
        return timeRented;
    }

    public void setTimeRented(int timeRented) {
        this.timeRented = timeRented;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
