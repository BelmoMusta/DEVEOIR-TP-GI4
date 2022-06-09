package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private Long dispo;
    private Long allouer;
    private String iduser;

    public String getIduser() {
        return iduser;
    }

    public void setDispo(Long dispo) {
        this.dispo = dispo;
    }

    public void setAllouer(Long allouer) {
        this.allouer = allouer;
    }

    public Long getDispo() {
        return dispo;
    }

    public Long getAllouer() {
        return allouer;
    }

    public void setIduser(String  iduser) {
        this.iduser = iduser;
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

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }
}
