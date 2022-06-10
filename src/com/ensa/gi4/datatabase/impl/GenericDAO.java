package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

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
    //Trouver toutes la liste des Matériels
    protected List<T> findAll(String query) {
        return (List<T>) jdbcTemplate.query(query, getRowMapper());
    }
    //Trouver un seul Materiel
    protected T findOneMateriel(String query, String code) {
        return (T) jdbcTemplate.queryForObject(query, getRowMapper(), code);
    }
    
    //Ajouter un Materiel Material
    protected void AddMaterial(String query,Materiel m) {
    	this.jdbcTemplate.update(query,m.getCode(),m.getName(),m.getDispo());
    }
    
    //Supprimer un Materiel 
    protected void RemoveMateriel(String query, String code) {
         this.jdbcTemplate.update(query,code);	
    }
    
   // Modifier Un Materiel
    protected void UpdateMateriel(String query,String code1,String code2) {
    	this.jdbcTemplate.update(query,code1,code2);
    }

    //Allouer materiel
    protected void allouerMateriel(String query,String name, String username) {
    	this.jdbcTemplate.update(query,name,username);
    }
    // Modifier Disponibilité
    protected void ModifierDisponibilite(String query,Boolean dispo,String code) {
    	this.jdbcTemplate.update(query,dispo,code);
    }
    //Supprimer Materiel Dans la table Allocation
    protected void supprimerMaterielAlloue(String query,String code) {
    	this.jdbcTemplate.update(query,code);
    }
    //Trouver un utilisateur
    protected T findUser(String query,String username,String password) {
    	return this.jdbcTemplate.queryForObject(query,getRowMapper(), username,password);
    }
    
    //Trouver le role de l'utilisateur
    protected String findRole(String query,String username) {
    	  return (String) this.jdbcTemplate.queryForObject(query,String.class,username);
    }
    
    //Trouver la liste de tous les Materiels alloués
    protected List<Allocation> findAllListAlloue(String query){
    	return (List<Allocation>) jdbcTemplate.query(query,getRowMapperAllocation());
    }
    
    //Trouver La liste des Matériels alloués par l'utilisateur lui meme
    protected List<Allocation> findListAlloueByName(String query,String username){
    	return (List<Allocation>) jdbcTemplate.query(query,getRowMapperAllocation(),username);
    }
    
    
    protected Allocation findMaterielAlloue(String query,String code) {
    	return jdbcTemplate.queryForObject(query, getRowMapperAllocation(),code);
    }
    
    protected abstract RowMapper<T> getRowMapper();
    
    protected abstract RowMapper<Allocation> getRowMapperAllocation();
    
}
