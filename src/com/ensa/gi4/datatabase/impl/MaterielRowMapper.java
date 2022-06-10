package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;

public class MaterielRowMapper implements RowMapper<Materiel> {
    @Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() {};

        String name = resultSet.getString("NAME");
        String code = resultSet.getString("CODE");
        Integer stock =  resultSet.getInt("STOCK"); 
        
        
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setStock(stock);
        
        return materiel;
    }
}
