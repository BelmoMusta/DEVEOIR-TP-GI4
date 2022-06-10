package com.ensa.gi4.modele;

public abstract class Materiel {
    protected Long id;
    protected String code;
    protected String name;

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }

    protected  boolean disponibility;

    public Integer getStock() {
        return stock;
    }

    protected Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
        return "\nname = " + name + ", code = " + code +", stock = "+stock+", disponibility = "+disponibility+"\n";
    }
}
