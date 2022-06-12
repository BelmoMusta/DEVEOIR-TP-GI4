package com.ensa.gi4.modele;

public abstract class Materiel {

    private int id;
    private String code;
    private String name;
    private int availability;
    private boolean _available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public boolean is_available() {
        return _available;
    }

    public void set_available(boolean _available) {
        this._available = _available;
    }

    public Materiel(){}

    public Materiel(String code, String name, int availability, boolean _available) {
        this.code = code;
        this.name = name;
        this.availability = availability;
        this._available = _available;
    }

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }
}
