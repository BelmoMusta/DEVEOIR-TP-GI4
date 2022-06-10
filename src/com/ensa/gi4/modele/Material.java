package com.ensa.gi4.modele;

public class Material {


    private long materialId;
    private String name;
    private String materialType;
    private boolean isAvailable;

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    @Override
    public String toString() {
        return "Material{" +
                "Id=" + materialId +
                ", name=" + name +
                ", materialType=" + materialType +
                ", Availability=" + isAvailable +
                '}';
    }
}