package com.ensa.gi4.datatabase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
				String query = "SELECT * FROM User WHERE name='"+name+"'";
				List<User> listUser = super.findAll(query);
				
				for( int i =0; i<listUser.size();i++) {
					if(BCrypt.checkpw(password, listUser.get(i).getPassword())) {
					
					user = listUser.get(i);
					break;
				}
			}
				 return user;
	}

	public User getUser(String name) {
		user = super.executeQuery("SELECT * FROM User WHERE name='"+name+"'");
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
		return super.executeForString("SELECT ROLE FROM USER WHERE name=?", name);
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
	
	
	
	@Override
	public void  afterPropertiesSet(){
		super.afterPropertiesSet();
		setHasherPassword();
	}
	
	//hacher un password 
	
	private String getHachPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(10));

	}
	
	
	
	// setter le password hacher dans BD
	
	private void setHasherPassword() {
		String query = "SELECT count (*)  FROM User";
		for (int i = 1; i <= super.executeForInt(query) ; i++) {
			String sql = "SELECT password FROM User WHERE id = " + i + "";;
			sql = "update user set password ='" + getHachPassword(super.executeForString2(sql)) + "' where id = " + i + "";
		    super.UpdateQuery(sql);
		    

		}
	}

	
}
