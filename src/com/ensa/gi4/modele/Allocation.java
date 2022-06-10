package com.ensa.gi4.modele;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public abstract class Allocation {
    private Long id;
    private Long mid;
    private Long uid;
    private Date date;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "\nuser id = " + uid + ", material id = " + mid +", date = "+date+"\n";
    }
}
