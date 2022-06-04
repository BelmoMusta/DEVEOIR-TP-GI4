package com.ensa.gi4.modele;


import java.util.Arrays;


public abstract class Materiel implements Entity {


    protected Integer id;
    protected String name;
    protected String type;
    protected String wood;
    protected String author;
    protected boolean allocated;




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

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }


    @Override
    public String toString() {
        return "Materiel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", allocated=" + allocated +
                '}';
    }
}
