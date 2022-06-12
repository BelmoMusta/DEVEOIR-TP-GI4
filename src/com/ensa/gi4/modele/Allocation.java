package com.ensa.gi4.modele;

public abstract class Allocation {
    private int idAllocation;
    private int idMateriel;
    private String nomMateriel;
    private int idUser;

    private String nomUser;

    private int duree;




    public int getIdAllocation() {
        return idAllocation;
    }

    public void setIdAllocation(int idAllocation) {
        this.idAllocation = idAllocation;
    }

    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public int getDuree() {
        return duree;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "idAllocation=" + idAllocation +
                ", idMateriel=" + idMateriel +
                ", nomMateriel='" + nomMateriel + '\'' +
                ", idUser=" + idUser +
                ", duree=" + duree +
                ", nomUser='" + nomUser + '\'' +
                '}';
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

}
