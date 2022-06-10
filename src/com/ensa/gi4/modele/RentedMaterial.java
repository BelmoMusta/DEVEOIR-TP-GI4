package com.ensa.gi4.modele;

import java.time.LocalDate;

public class RentedMaterial {
    private Long id;
    private Long userId;
    private Material material;
    private LocalDate deadline;

    public RentedMaterial() {
        material = new Material();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
