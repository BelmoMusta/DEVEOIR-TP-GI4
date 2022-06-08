package com.ensa.gi4.modele;




public abstract class Materiel implements Entity {


    protected Integer id;
    protected String name;
    protected String type;
    protected String wood;
    protected String author;
    protected String edition;
    protected Integer stock;
    protected Integer available;




    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getAllocatedCount(){
        return this.stock-this.available;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof Materiel)) return false;
        else return this.getId().equals(((Materiel) obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
