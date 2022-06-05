package com.ensa.gi4.datatabase.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.PersonneDAO;

import com.ensa.gi4.modele.Personne;
@Component 
public class PersonneDaoImpl extends GenericDAO<Personne> implements PersonneDAO{

	@Override
	protected RowMapper<Personne> getRowMapper() {
		// TODO Auto-generated method stub
		return new PersonneRowMapper();
	}

	@Override
	public Personne findPersonne(String nom, String pw) {
	//	try {
		String sql = "Select * from users where name ='"+nom+"' and pw = '"+pw+"'";
		return super.executeQuery(sql);
		//}
		/* catch (Exception e) {
			return null;
		}*/
		
		
	}

}
