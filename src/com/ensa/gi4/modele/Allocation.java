package com.ensa.gi4.modele;

public class Allocation {
    private int allocation_id;
    private int user_id;
    private int material_id;
    private int duration;

    public Allocation() {
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
        return "Allocation{" +
                "allocation_id=" + allocation_id +
                ", user_id=" + user_id +
                ", material_id=" + material_id +
                ", duration=" + duration +
                '}';
    }
}
