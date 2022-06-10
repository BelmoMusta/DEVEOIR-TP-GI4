package com.ensa.gi4.modele;

public class AllocatedItem {
    private int id_user ;
    private int id_materiel ;

    public AllocatedItem() {
    }

    public AllocatedItem(int id_user, int id_materiel) {
        this.id_user = id_user;
        this.id_materiel = id_materiel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_materiel() {
        return id_materiel;
    }

    public void setId_materiel(int id_materiel) {
        this.id_materiel = id_materiel;
    }
}
