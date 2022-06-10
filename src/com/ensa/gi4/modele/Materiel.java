package com.ensa.gi4.modele;

public abstract class Materiel {

    private int id;
    private String code;
    private String name;
    private String user_id;
    private String username;
    private Boolean dispo;
    private Boolean alloue;
    private String type;
    private String duree;

    public Boolean getAlloue() {
        return alloue;
    }

    public void setAlloue(Boolean alloue) {
        this.alloue = alloue;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", alloue=" + alloue +
                ", duree='" + duree + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
