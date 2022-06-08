package com.ensa.gi4.modele;

public class Allocation {
    private int allocation_id;
    private int user_id;
    private int material_id;
    private int duration;

    //Related table data
    private User user;
    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Allocation() {
    }

    public Allocation( int user_id, int material_id, int duration) {
        this.user_id = user_id;
        this.material_id = material_id;
        this.duration = duration;
    }

    public Allocation(int allocation_id, int user_id, int material_id, int duration) {
        this.allocation_id = allocation_id;
        this.user_id = user_id;
        this.material_id = material_id;
        this.duration = duration;
    }

    public int getAllocation_id() {
        return allocation_id;
    }

    public void setAllocation_id(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return
                "Allocation Id = " + allocation_id +
                ", Borrowed by = " + user.getUsername() +
                ", Who is an " + user.getRole().getName() +
                ", User id = " + user_id +
                ", Material Name = " + material.getName() +
                ", Material Id = " + material_id ;
    }
}
