package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };

        int id_ = resultSet.getInt("id");
        String name_ = resultSet.getString("NAME");
        String code_ = resultSet.getString("CODE");
        boolean dispo_ = resultSet.getBoolean("dispo");
        int userId_ = resultSet.getInt("userId");
        
        materiel.setId(id_);
        materiel.setCode(code_);
        materiel.setName(name_);
        materiel.setDispo(dispo_);
        materiel.setUserId(userId_);
        

        return materiel;
    }
}
