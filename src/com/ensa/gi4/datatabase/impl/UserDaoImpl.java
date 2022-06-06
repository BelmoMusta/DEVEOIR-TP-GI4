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
		 return super.findOne("SELECT * FROM User WHERE name=? AND password=?;", name,password);
	
		
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
		System.out.println(super.getRole("SELECT ROLE FROM USER WHERE name=?", name));
		return super.getRole("SELECT ROLE FROM USER WHERE name=?", name);
	}
	//allocation
	public void allouerMateriel(String code, String duree) {
		String query = "INSERT INTO Allocation (idMateriel,idUser,duree) values("
				+ materielDao.findWithCode(code).getId() + "," + user.getId() + ",'" + duree + "')";
		super.UpdateQuery(query);
	}
}
