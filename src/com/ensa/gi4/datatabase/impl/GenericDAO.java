package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

    protected List<T> findAll(String query) {

        return jdbcTemplate.query(query, getRowMapper());
    }

    protected T findOne(String query, Long id) {

        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    protected int deleteOne(String query, Long id) {

        return jdbcTemplate.update(query, id);
    }

    protected int addOne(String query, String name, String code, String type, Long stock, Boolean disponibility) {

        return jdbcTemplate.update(query, name, code, type, stock, disponibility);

    }

    protected abstract RowMapper<T> getRowMapper();



    protected  T findUser(String query, String userName, String password) {

        return jdbcTemplate.queryForObject(query, getRowMapper(), userName, password);
    }

    protected List<T>  findAllUser(String query) {

        return jdbcTemplate.query(query, getRowMapper());

    }

    protected Map<String, Object> findUserId(String query, String name, String role) {

        return jdbcTemplate.queryForMap(query, name, role);

    }


    protected List<T> findAllAllocatedMaterialsForUser(String query, Long idUser) {

        return jdbcTemplate.query(query, getRowMapper(), idUser);

    }


    protected int allocateOne(String query, Long idUser, Long idMateriel) {

        return jdbcTemplate.update(query, idUser, idMateriel);

    }

    protected int resetTheStock(String query, Long idMateriel) {

        return jdbcTemplate.update(query, idMateriel);

    }

    protected int deleteReturnedMaterial(String query, Long idMateriel) {

        return jdbcTemplate.update(query, idMateriel);

    }

    protected int noDipoMateriel(String query, Long idMateriel) {

        return jdbcTemplate.update(query, idMateriel);

    }

    protected int updateTextMateriel(String query, String modificationTextuel, Long idMateriel) {

        return jdbcTemplate.update(query, modificationTextuel, idMateriel);

    }

    protected int updateStockMateriel(String query, Long modificationNumerique, Long idMateriel) {

        return jdbcTemplate.update(query, modificationNumerique, idMateriel);

    }





}
