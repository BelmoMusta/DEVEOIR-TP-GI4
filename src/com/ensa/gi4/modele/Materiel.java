package com.ensa.gi4.modele;

public abstract class Materiel {
    private String code;
    private String name;
    private int disponible;
    private int alloué;
    private int employee;

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
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
