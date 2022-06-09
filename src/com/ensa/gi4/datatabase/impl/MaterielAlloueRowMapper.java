package com.ensa.gi4.datatabase.impl;


import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAlloue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MaterielAlloueRowMapper implements RowMapper<MaterielAlloue> {

    public MaterielAlloue mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterielAlloue materielAlloue = new MaterielAlloue() { // because it is abstract
        };

        Long id = resultSet.getLong("ID");
        Long idUser = resultSet.getLong("IDUSER");
        Long idMateriel = resultSet.getLong("IDMAT");
        Timestamp ts = resultSet.getTimestamp("ALLOCATIONDURATION");

        System.out.println(id);
        System.out.println(idUser);
        System.out.println(idMateriel);
        System.out.println(ts);




        materielAlloue.setIdAlloc(id);
        materielAlloue.setMaterielId(idMateriel);
        materielAlloue.setUserId(idUser);
        materielAlloue.setDateAlloc(ts);

        return materielAlloue;

    }

}
