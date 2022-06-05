package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Personne;



public class PersonneRowMapper implements RowMapper<Personne> {

	@Override
	public Personne mapRow(ResultSet resultSet, int arg1) throws SQLException {
		 Personne personne = new Personne() { // because it is abstract
	        };

	        String name = resultSet.getString("name");
	        //String name_ = resultSet.getString("NAME");
	        //String code_ = resultSet.getString("CODE");
	        String pw = resultSet.getString("pw");
	        String role = resultSet.getString("role");
	        personne.setName(name);
	        personne.setPw(pw);
	        personne.setRole(role);

	        return personne;
	}

}
