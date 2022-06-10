package com.ensa.gi4.datatabase.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;

public abstract class GenericDAO<T> implements InitializingBean {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    // materiel operation 
    
    protected int ajouter(String query, Materiel materiel) {
    	return jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getStock(), true); 
    }
    
    protected int  supprimer(String query, String code, Integer idMateriel) {
		return jdbcTemplate.update(query, code, idMateriel); 
	}
    
    protected int  update(String query, String code, Integer stock, String ancienCode) {
		return jdbcTemplate.update(query, code, stock, ancienCode); 
	}
    
    protected Optional<Map<String, Object>> findIdMateriel(String query, String code) {
    	 try {
 			return Optional.of(jdbcTemplate.queryForMap(query, code)); 
 	    } catch (EmptyResultDataAccessException e) {
 	    	return Optional.empty();
 	    }
    	
    }
    
    protected Optional<Map<String, Object>>  nombreMaterielAlloue(String query, Integer idMateriel) {
				
		try {
 			return Optional.of(jdbcTemplate.queryForMap(query, idMateriel)); 
 	    } catch (EmptyResultDataAccessException e) {
 	    	return Optional.empty();
 	    }
	}
    
    protected Optional<List<Map<String, Object>>>  materielAllouable(String query, Integer idMateriel) {
    	
    	try {
 			return Optional.of(jdbcTemplate.queryForList(query, idMateriel)); 
 	    } catch (EmptyResultDataAccessException e) {
 	    	return Optional.empty();
 	    }

	}
    
    protected int materielIndisponible(String query, Boolean isAvailable ,Integer id) {
    	return jdbcTemplate.update(query, isAvailable, id); 
    	
    	
    }

    protected Optional<List<T>> findAll(String query) { 
        try {
			return Optional.of(jdbcTemplate.query(query, getRowMapper())); 
	    } catch (EmptyResultDataAccessException e) {
	    	return Optional.empty();
	    }
    }

    protected Optional<T> findOne(String query, String code, String name) {
    	try {
 			return Optional.of(jdbcTemplate.queryForObject(query, getRowMapper(), code, name)); 
 	    } catch (EmptyResultDataAccessException e) {
 	    	return Optional.empty();
 	    }
      
    }
    
    protected int  allouerMateriel(String query, Integer idMateriel, Integer idUser, Timestamp timestamp) {
		return jdbcTemplate.update(query, idMateriel, idUser, timestamp); 
	}
    
    protected int  rendreMateriel(String query, Integer idMateriel, Integer idUser) {
    	return jdbcTemplate.update(query, idMateriel, idUser); 
	}
    
    protected Optional<List<Materiel>> listeMaterielAlloue(String query, Integer idUser, MaterielAllouerRowMapper materielAllouerRowMapper) {
		try {
			return Optional.of(jdbcTemplate.query(query, materielAllouerRowMapper, idUser)); 
	    } catch (EmptyResultDataAccessException e) {
	    	return Optional.empty();
	    }
	}

    
    protected Optional<List<T>> listeMatereilAlloueParListUser(String query) {
	    try {
				return Optional.of(jdbcTemplate.query(query, getRowMapper())); 
		    } catch (EmptyResultDataAccessException e) {
		    	return Optional.empty();
	    }
	}

    protected abstract RowMapper<T> getRowMapper();
    
    // user operation 
    
    protected  Optional<T> findUser(String query, List<String> userData) {
    	try {
    			return Optional.of(jdbcTemplate.queryForObject(query, getRowMapper(), userData.get(0), userData.get(1))); 
    	    } catch (EmptyResultDataAccessException e) {
    	    	return Optional.empty();
    	    }
    }
    
    protected Optional<List<T>>  findAllUser(String query) {
    	
    	try {
			return Optional.of(jdbcTemplate.query(query, getRowMapper())); 
	    } catch (EmptyResultDataAccessException e) {
	    	return Optional.empty();
	    }
  
	}
    
    protected Optional<List<Map<String, Object>>> findAllUserAndPassword(String query) {
    	try {
			return Optional.of(jdbcTemplate.queryForList(query)); 
	    } catch (EmptyResultDataAccessException e) {
	    	return Optional.empty();
	    }
	}
    
    protected Optional<Map<String, Object>> findUserId(String query, String name, String role) {	
		try {
			return Optional.of(jdbcTemplate.queryForMap(query, name, role)); 
	    } catch (EmptyResultDataAccessException e) {
	    	return Optional.empty();
	    }
	
	}
}
