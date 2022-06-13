package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel();
        materiel.setMatId(resultSet.getInt("matId"));
        materiel.setName(resultSet.getString("name"));
        materiel.setType(resultSet.getString("type"));
        materiel.setAlloue(resultSet.getBoolean("alloue"));
        materiel.setUserId(resultSet.getInt("userid"));
        return materiel;
    }
}
