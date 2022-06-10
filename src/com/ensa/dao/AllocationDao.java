package com.ensa.dao;

import com.ensa.datatabase.impl.GenericDAO;
import com.ensa.modele.Allocation;
import com.ensa.modele.Chaise;
import com.ensa.modele.Materiel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
@Component
public class AllocationDao extends GenericDAO<Allocation> implements DAO, InitializingBean {


    @Override
    public List<Allocation> findAll() {
        return super.findAll("SELECT * FROM ALLOCATION;");
    }
    public void ajouter(Allocation a) {

        try {
            jdbcTemplate.update("insert into allocation (userid, allocationdate,productid) values (?, ?,?)", a.getUserid(), a.getAllocationdate(), a.getProductid());
        } catch (Exception e) {
            System.out.println("Deja allouer");
        }

    }
    public void supprimer(String code){ super.jdbcTemplate.update("DELETE FROM ALLOCATION WHERE PRODUCTID=?;",code);}

    @Override
    public Materiel findOne(Long id) {
        return null;
    }
    public List<Allocation>findbyCodeList(String code){try{ return super.findByCodeList("SELECT * FROM ALLOCATION WHERE USERID=?;",code);}catch (EmptyResultDataAccessException e) {
        return null;
    }}


    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return new AllocationRowMapper();
    }
}
