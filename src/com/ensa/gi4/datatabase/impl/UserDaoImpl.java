package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;

@Repository
public class UserDaoImpl extends GenericDAO<User> implements UserDao{

	User user;
	@Autowired 
	MaterielDao materielDao;
	@Override
	public User findOneUser(String name,String password) {
	
				user = super.findOne("SELECT * FROM User WHERE name=? AND password=?;", name,password);
	
				 return user;
	}


	@Override
	public List<User> finAllUsers() {
		return  super.findAll("SELECT * FROM User");
		
	}

	
	@Override
	protected RowMapper<User> getRowMapper() {
		return new UserRowMapper();
	}


	@Override
	public String getRole(String name) {
		return super.getRole("SELECT ROLE FROM USER WHERE name=?", name);
	}
	//allocation
	public void allouerMateriel(String code, String duree) {
	


		String query ="UPDATE materiel set allouer=" +user.getId()+ ", duree = '"+duree+"'WHERE allouer IS NULL and dispo = true limit 1";
		super.UpdateQuery(query);
	}
	
	//rendre
	@Override
	public Boolean rendreMateriel(int id) {
		String query  = "UPDATE materiel SET allouer= null, duree = null where allouer="+user.getId()+" and id="+id+"";
		super.UpdateQuery(query);
        return true;
	 
		
	}
}
