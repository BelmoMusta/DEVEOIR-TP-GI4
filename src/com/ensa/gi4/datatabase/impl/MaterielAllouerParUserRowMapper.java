package com.ensa.gi4.datatabase.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllouer;
import com.ensa.gi4.modele.Role;
import com.ensa.gi4.modele.User;

public class MaterielAllouerParUserRowMapper implements RowMapper<MaterielAllouer> {

	@Override
	public MaterielAllouer mapRow(ResultSet resultSet, int arg1) throws SQLException {
		Materiel materiel = new Materiel() {};
		User user = new User(); 
		MaterielAllouer materielAllouer = new MaterielAllouer();
	        
        String name = resultSet.getString("MATERIELNAME");
        String code = resultSet.getString("CODE");
        Integer stock =  resultSet.getInt("STOCK"); 
        Timestamp timestamp =  resultSet.getTimestamp("DATEALLOCATION"); 
        materiel.setCode(code);
        materiel.setName(name);
        materiel.setStock(stock);
        materiel.setTimestamp(timestamp);
       
		String nameUser = resultSet.getString("USERNAME");
		String role = resultSet.getString("role"); 
		
		user.setName(nameUser);
		
		if(Role.ADMIN.toString().equals(role))
			user.setRole(Role.ADMIN);
		else {
			user.setRole(Role.USER);
		}
		
		materielAllouer.setMateriel(materiel);
		materielAllouer.setUser(user);
		
		return materielAllouer;
	}

}
