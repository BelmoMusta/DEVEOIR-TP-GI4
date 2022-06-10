package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDAO<T> implements InitializingBean {
	
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() { // from InitializingBean
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //La m�thode afficherMateriel()
    protected List<T> afficherMateriel(String query) {
    	
    	return jdbcTemplate.query(query, getRowMapper());
    }
    
    //La m�thode chercherMateriel(Long idMateriel)
    protected T chercherMateriel(String query, Long idMateriel) {
    	
    	return jdbcTemplate.queryForObject(query, getRowMapper(), idMateriel);
    }
    
    //La m�thode allouerMateriel(Long idMateriel)
    protected int allouerMateriel(String query, String etat, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, etat, idMateriel);
    	
    }
    
    //La m�thode ajouterMaterielUser(Long idUser, Long idMateriel)
    protected int ajouterMaterielUser(String query, Long idUser, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, idUser, idMateriel);
    }
    
    //La m�thode afficherMaterielUtilisateur(Utilisateur utilisateur)
    protected List<T> afficherMaterielUser(String query, Long idUser) {
    	
    	return jdbcTemplate.query(query, getRowMapper());
    }
    
    //La m�thode rendreMateriel(Long idMateriel)
    protected int rendreMateriel(String query,String etat, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, etat, idMateriel);
    }
    
    //La m�thode ajouterNouveauMateriel(Materiel materiel)
    protected int ajouterNouveauMateriel(String query, Materiel materiel) {
    	
    	return jdbcTemplate.update(query, null, materiel.getName(), materiel.getCode(), materiel.getEtat());
    }
    
    //La m�thode supprimerMateriel(Long idMateriel)
    protected int supprimerMateriel(String query, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, idMateriel);
    }
    
    //La m�thode modifierMateriel(Materiel materiel, Long idMateriel)
    protected int modifierMateriel(String query, Materiel materiel, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, materiel.getName(), materiel.getCode(), materiel.getEtat(), idMateriel);
    }
    
    //La m�thode materielIndisponible(String etat, Long idMateriel)
    protected int materielIndisponible(String query, String etat, Long idMateriel) {
    	
    	return jdbcTemplate.update(query, etat, idMateriel);
    }
    
    //La m�thode afficherMaterielEveryUser()
    protected List<T> afficherMaterielEveryUser(String query) {
    	
    	return jdbcTemplate.query(query, getRowMapper());
    }
    
    //La m�thode login(Utilisateur utilisateur)
    protected T login(String query, Utilisateur utilisateur) {
    	
    	return jdbcTemplate.queryForObject(query, getRowMapper(), utilisateur.getUsername(), utilisateur.getPassword());
    	
    }
    
    
    
    protected List<T> findAll(String query) {
        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, Long id) {
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected abstract RowMapper<T> getRowMapper();
    
    protected RowCallbackHandler rch;
}
