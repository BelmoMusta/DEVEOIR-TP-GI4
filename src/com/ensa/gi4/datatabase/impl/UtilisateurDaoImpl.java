package com.ensa.gi4.datatabase.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Utilisateur;

@Component 
public class UtilisateurDaoImpl extends GenericDAO<Utilisateur> implements UtilisateurDao{

	@Override
	protected RowMapper<Utilisateur> getRowMapper() {
		// TODO Auto-generated method stub
		return new UtilisateurRowMapper();
	}

	@Override
	public Utilisateur findUtilisateur(String nom, String pw) {
		try {
			String sql = "Select * from utilisateur where username ='"+nom+"' and password = '"+pw+"'";
			return super.executeQuery(sql);
		}catch(DataAccessException e){
			return null;
		}
		
		
	}
}