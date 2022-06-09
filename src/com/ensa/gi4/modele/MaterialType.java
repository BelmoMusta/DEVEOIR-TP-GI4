package com.ensa.gi4.modele;

public class MaterialType {

    private Long id;
    private String name;

    public MaterialType() {}

    public MaterialType(String name) {
        this.name = name;
    }

    public MaterialType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
