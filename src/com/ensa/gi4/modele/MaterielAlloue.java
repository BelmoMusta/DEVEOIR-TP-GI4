package com.ensa.gi4.modele;

import java.sql.Timestamp;
import java.util.Date;

public class MaterielAlloue {

    private Long idAlloc;

    private Long userId;

    private Long materielId;

    private Timestamp dateAlloc;


    public Timestamp getDateAlloc() {

        return dateAlloc;

    }

    public void setDateAlloc(Timestamp dateAlloc) {

        this.dateAlloc = dateAlloc;

    }

    public Long getIdAlloc() {

        return idAlloc;

    }

    public void setIdAlloc(Long idAlloc) {

        this.idAlloc = idAlloc;

    }

    public Long getMaterielId() {

        return materielId;

    }

    public void setMaterielId(Long materielId) {

        this.materielId = materielId;

    }

    public Long getUserId() {

        return userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;

    }


    @Override
    public String toString() {
        return "MaterielAlloue{" +
                "idAlloc=" + idAlloc +
                ", userId=" + userId +
                ", materielId=" + materielId +
                ", dateAlloc=" + dateAlloc +
                '}';
    }
}
