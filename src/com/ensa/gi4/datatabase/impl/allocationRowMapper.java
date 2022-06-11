package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Allocation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class allocationRowMapper implements RowMapper<Allocation> {
    @Override
    public Allocation mapRow(ResultSet resultSet, int i) throws SQLException {
        Allocation allocation = new Allocation() {
        };
        int idAllocation = resultSet.getInt(1);
        int idMateriel = resultSet.getInt(2);
        String nomMateriel = resultSet.getString(3);
        int idUser = resultSet.getInt(4);
        String nomUser = resultSet.getString(5);
        int duree = resultSet.getInt(6);



        allocation.setIdAllocation(idAllocation);
        allocation.setIdMateriel(idMateriel);
        allocation.setNomMateriel(nomMateriel);
        allocation.setIdUser(idUser);
        allocation.setNomUser(nomUser);
        allocation.setDuree(duree);
        return allocation;
    }
}
