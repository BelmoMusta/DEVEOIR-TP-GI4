package com.ensa.gi4.modele;

public class MaterielAllocated {
    public int getIdMaterielAllocated() {
        return idMaterielAllocated;
    }

    public void setIdMaterielAllocated(int idMaterielAllocated) {
        this.idMaterielAllocated = idMaterielAllocated;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNb_days() {
        return nb_days;
    }

    public void setNb_days(int nb_days) {
        this.nb_days = nb_days;
    }

    public int getIdUs() {
        return idUs;
    }

    public void setIdUs(int idUs) {
        this.idUs = idUs;
    }

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    private int idMaterielAllocated;
    private int quantity;
    private int nb_days;
    private int idUs;
    private int idMat;

    @Override
    public String toString() {
        return
                "\n Allocation N° : " + idMaterielAllocated +
                "\n Du matériel N° : " + idMat +
                "\n Par l'utilisateur N° : " + idUs +
                "\n Quantité Allouée : " + quantity +
                "\n Nombre de jours : " + nb_days +
                "\n ------------------";
    }
}
