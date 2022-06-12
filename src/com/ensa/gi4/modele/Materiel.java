package com.ensa.gi4.modele;

public abstract class Materiel {
    private String type;
    private String name;

    private  String code;

    private Boolean isAvailable;

    public Boolean getAvailable() {
        return isAvailable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
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
        return "{Materiel de " +
                "type='" + type + '\'' +
                ", nom='" + name + '\'' +
                ", code='" + code + '\'' +
                ", disponible=" + isAvailable +
                '}';
    }
}
