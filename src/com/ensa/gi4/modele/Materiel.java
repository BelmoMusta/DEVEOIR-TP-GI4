package com.ensa.gi4.modele;

public class Materiel {

    private Long id;
    private String name;
    private String code;
    private String type;
    private Long stock;
    private boolean dispo;




    public Long getId() {

        return this.id;
    }
    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return this.name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getCode() {

        return this.code;
    }
    public void setCode(String code) {

        this.code = code;
    }

    public String getType() {

        return this.type;

    }
    public void setType(String type) {

        this.type = type;

    }

    public Long getStock() {

        return this.stock;
    }
    public void setStock(Long stock) {

        this.stock = stock;
    }

    public boolean getDispo() {

        return this.dispo;

    }
    public void setDispo(boolean dispo) {

        this.dispo = dispo;

    }

    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                ", dispo=" + dispo +
                '}';
    }
}
