package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;

public class ChaiseRowMapper implements RowMapper<Materiel> {

	@Override
	public Materiel mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Chaise chaise=new Chaise();
		chaise.setId(resultSet.getInt("id"));
		chaise.setCode(resultSet.getString("CODE"));
		chaise.setName(resultSet.getString("NAME"));
		chaise.setStock(resultSet.getInt("stock"));
		chaise.setAvailable(resultSet.getBoolean("disponibilite"));
		return chaise;
	}

	
}
