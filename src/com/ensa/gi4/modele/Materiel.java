package com.ensa.gi4.modele;

public abstract class Materiel {
    private int idMateriel;
    private String code;
    private String name;
    private String typeMateriel;
    private boolean isDisponible;
    private boolean isAllouer;

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Materiel{" +
                "idMateriel=" + idMateriel +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", typeMateriel='" + typeMateriel + '\'' +
                ", isDisponible=" + isDisponible +
                ", isAllouer=" + isAllouer +
                '}';
    }
}
