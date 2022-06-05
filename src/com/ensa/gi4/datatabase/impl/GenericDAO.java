package com.ensa.gi4.datatabase.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    protected Map<String, Object> findMaterielId(String query, String code) {
    	return jdbcTemplate.queryForMap(query, code); 
    }
    
    protected Map<String, Object>  nombreMaterielAlloue(String query, Integer idMateriel) {
		return jdbcTemplate.queryForMap(query, idMateriel); 
	}
    
    protected List<Map<String, Object>>  materielAllouable(String query, Integer idMateriel) {
		return jdbcTemplate.queryForList(query, idMateriel); 
	}
    
    protected int materielIndisponible(String query, Boolean isAvailable ,Integer id) {
    	return jdbcTemplate.update(query, isAvailable, id); 
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, String code, String name) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), code, name);
    }
    
    protected int  allouerMateriel(String query, Integer idMateriel, Integer idUser, Timestamp timestamp) {
		return jdbcTemplate.update(query, idMateriel, idUser, timestamp); 
	}
    
    protected int  rendreMateriel(String query, Integer idMateriel, Integer idUser) {
    	return jdbcTemplate.update(query, idMateriel, idUser); 
	}
    
    protected List<Materiel> listeMaterielAlloue(String query, Integer idUser, MaterielAllouerRowMapper materielAllouerRowMapper) {
		return jdbcTemplate.query(query, materielAllouerRowMapper, idUser); 
	}

    
    protected List<T> listeMatereilAlloueParListUser(String query) {
    	return jdbcTemplate.query(query, getRowMapper()); 
	}

    protected abstract RowMapper<T> getRowMapper();
    
    // user operation 
    
    protected  T findUser(String query, List<String> userData) {
    	return jdbcTemplate.queryForObject(query, getRowMapper(), userData.get(0), userData.get(1)); 
    }
    
    protected List<T>  findAllUser(String query) {
		return jdbcTemplate.query(query, getRowMapper()); 
	}
    
    protected Map<String, Object> findUserId(String query, String name, String role) {
		return jdbcTemplate.queryForMap(query, name, role);
	}
}
