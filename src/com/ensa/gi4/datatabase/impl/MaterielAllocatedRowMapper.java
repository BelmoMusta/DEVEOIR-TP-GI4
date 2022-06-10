package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.MaterielAllocated;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielAllocatedRowMapper implements RowMapper<MaterielAllocated> {
    @Override
    public MaterielAllocated mapRow(ResultSet resultSet, int i) throws SQLException {
        MaterielAllocated materielAllocated = new MaterielAllocated() { // because it is abstract
        };
        int id = resultSet.getInt(1);
        int quantity = resultSet.getInt(4);
        int nb_days = resultSet.getInt(5);
        int idUs = resultSet.getInt(2);
        int idMat = resultSet.getInt(3);
        materielAllocated.setIdMaterielAllocated(id);
        materielAllocated.setQuantity(quantity);
        materielAllocated.setNb_days(nb_days);
        materielAllocated.setIdUs(idUs);
        materielAllocated.setIdMat(idMat);
        return materielAllocated;
    }
}
