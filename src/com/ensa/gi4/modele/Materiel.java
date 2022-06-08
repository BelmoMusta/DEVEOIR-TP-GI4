package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private int disponible;
    private int alloué;
    public int epuise;

    public String getName() {
        return name;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getAlloué() {
        return alloué;
    }

    public void setAlloué(int alloué) {
        this.alloué = alloué;
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

    public int getEpuise() {
        return epuise;
    }

    public void setEpuise(int epuise) {
        this.epuise = epuise;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }


}
