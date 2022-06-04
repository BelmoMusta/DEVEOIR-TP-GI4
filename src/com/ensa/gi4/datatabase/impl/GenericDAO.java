package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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
    
    protected int  supprimer(String query, String code) {
		return jdbcTemplate.update(query, code); 
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
    
    protected Map<String, Object>  nombreMaterielStock(String query, Integer idMateriel) {
		return jdbcTemplate.queryForMap(query, idMateriel); 
	}
    
    protected int materielIndisponible(String query, Boolean isAvailable ,Integer id) {
    	return jdbcTemplate.update(query, isAvailable, id); 
    }

    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, Long id) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected abstract RowMapper<T> getRowMapper();
    
    // user operation 
    
    protected  T findUser(String query, List<String> userData) {
    	return jdbcTemplate.queryForObject(query, getRowMapper(), userData.get(0), userData.get(1)); 
    }
    
    protected Map<String, Object> findUserId(String query, String name, String role) {
		return jdbcTemplate.queryForMap(query, name, role);
	}
}
