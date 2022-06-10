package com.ensa.gi4.datatabase.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

    protected List<T> findAll(String query) {
        
         try {
                    return jdbcTemplate.query(query, getRowMapper());

            }
   catch(Exception e) {
        return null;
    }
        
        
    }

    protected T findOne(String query, String id) {
        
             try {
                   return jdbcTemplate.queryForObject(query, getRowMapper(), id);

            }
   catch(Exception e) {
        return null;
    }
        
        
    }
    
    protected int addOne(String query, String name, String code, int qte) {
        return jdbcTemplate.update(query, name, code, qte);
    }

    protected abstract RowMapper<T> getRowMapper();
    
    //pour trouver un utilisateur à partir de son MDP et son userName
    protected T findEntity(String query, String username, String password)
    {
        
                try {
                   return jdbcTemplate.queryForObject(query, getRowMapper(), username, password);

            }
   catch(Exception e) {
        return null;
    }
                
        
    }
    
    protected void removeOne(String query, String code) {
       
        jdbcTemplate.update(query, code);
    }
    
    protected void updateOne(String query, String name, String code, int qte, boolean disponible, int id) {
       
        jdbcTemplate.update(query, name, code, qte, disponible, id);
    }

    //pour trouver un utilisateur à partir de son MDP et son userName
    protected List<T> findSome(String query, int idM, boolean rendu)
    {
                  try {
                   return jdbcTemplate.query(query, getRowMapper(), idM, rendu);

            }
   catch(Exception e) {
        return null;
    }
                  
        
    }
    
    protected int addEntity(String query, int qta, boolean rendu, int idM, int idU) {
        return jdbcTemplate.update(query, qta, rendu,idM, idU);
    }
    
     protected T Exist(String query, int idM, int idU) {
         
                    try {
                   return jdbcTemplate.queryForObject(query, getRowMapper(), idU, idM);

            }
   catch(Exception e) {
        return null;
    }
                    
        
    }
    
}
