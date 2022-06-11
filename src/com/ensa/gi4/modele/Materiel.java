package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private String user;
    private  boolean disponible;
    private boolean alloue;

    public boolean isAlloue() {
        return alloue;
    }

    public void setAlloue(boolean alloue) {
        this.alloue = alloue;
    }

    public boolean isNonAlloue() {
        return nonAlloue;
    }

    public void setNonAlloue(boolean nonAlloue) {
        this.nonAlloue = nonAlloue;
    }

    private boolean nonAlloue;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
