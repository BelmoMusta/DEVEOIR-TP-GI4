package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private Boolean disponible=true;

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

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean dispo) {
        this.disponible = dispo;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }
}
