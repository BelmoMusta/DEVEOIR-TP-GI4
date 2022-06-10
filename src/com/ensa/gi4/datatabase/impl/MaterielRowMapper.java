package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet rs, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };
        materiel.setId(rs.getInt("id"));
        materiel.setName(rs.getString("name"));
        materiel.setStock(rs.getInt("stock"));
        materiel.setAllocated(rs.getInt("allocated"));
        materiel.setType(rs.getInt("type"));
        return materiel;
    }
}
