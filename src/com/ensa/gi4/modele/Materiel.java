package com.ensa.gi4.modele;

public abstract class Materiel {
    
    private int idM;
    private String code;
    private String name;
    private int qte;
    private boolean disponible;

    public String getName() {
        return name;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
   public boolean getDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code + " Qte= "+ qte;
    }
}
