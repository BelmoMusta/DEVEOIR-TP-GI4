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

//        String name = resultSet.getString(2);
//        String code = resultSet.getString(3);
        
        String name = resultSet.getString("name");
        String code = resultSet.getString("code");
        
        materiel.setCode(code);
        materiel.setName(name);

        return materiel;
    }
}
