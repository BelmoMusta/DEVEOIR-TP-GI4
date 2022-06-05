package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;

public class MaterielAllouerRowMapper implements RowMapper<Materiel> {

	@Override
    public Materiel mapRow(ResultSet resultSet, int i) throws SQLException {
        Materiel materiel = new Materiel() { // because it is abstract
        };

        
        String name = resultSet.getString("NAME");
        String code = resultSet.getString("CODE");
        Integer stock =  resultSet.getInt("STOCK"); 
    	Timestamp timestamp =  resultSet.getTimestamp("DATEALLOCATION"); 

        materiel.setCode(code);
        materiel.setName(name);
        materiel.setStock(stock);
        materiel.setTimestamp(timestamp);
        
        return materiel;
    }

	

}
