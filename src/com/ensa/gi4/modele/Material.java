package com.ensa.gi4.modele;

public  class Material {
    private int material_id;
    private int quantity;
    private String material_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Material()
    {

    }

    public Material(String name, int quantity, String material_type) {
        this.quantity = quantity;
        this.material_type = material_type;
        this.name = name;
    }

    public Material(int material_id,String name, int quantity, String material_type) {
        this.material_id = material_id;
        this.quantity = quantity;
        this.material_type = material_type;
        this.name = name;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    @Override
    public String toString() {
        return
                "Id = " + material_id +
                ", Quantity = " + quantity +
                ", Type = '" + material_type + '\'' +
                ", Name = '" + name + '\'';
    }
}
