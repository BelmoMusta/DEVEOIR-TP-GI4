package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private int idMateriel;
    private String typeMateriel;
    private boolean isDisponible;
    private boolean isAllouer;

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
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
    public String getTypeMateriel() {
        return typeMateriel;
    }

    public void setTypeMateriel(String typeMateriel) {
        this.typeMateriel = typeMateriel;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }

    public boolean isAllouer() {
        return isAllouer;
    }

    public void setAllouer(boolean allouer) {
        isAllouer = allouer;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }
}
