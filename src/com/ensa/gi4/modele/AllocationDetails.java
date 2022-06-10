package com.ensa.gi4.modele;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AllocationDetails implements Entity{
    private Integer id;
    private User user;
    private Materiel materiel;
    private Timestamp date;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "AllocationDetails{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", materiel=" + materiel.getName() +
                ", date=" + simpleDateFormat.format(new Date(getDate().getTime())) +
                '}';
    }
}
