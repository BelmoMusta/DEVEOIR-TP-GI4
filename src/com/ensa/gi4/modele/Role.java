package com.ensa.gi4.modele;

public class Role implements Entity{

    private Integer id;
    private String name;

    @Override
    public Integer getId() {
        return this.id;
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

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else if (!(obj instanceof Role)) return false;
        return this.getId().equals(((Role)obj).getId());
    }
}
