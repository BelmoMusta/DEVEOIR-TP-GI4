package com.ensa.gi4.modele;

public abstract class Materiel {
    private Long id;
    private String code;
    private String name;
    private boolean rented;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isRented(){return this.rented;}

    public void setRented(Boolean rented){this.rented = rented;}

    @Override
    public String toString() {
        return "name = " + name + ", code = " + code;
    }

}
