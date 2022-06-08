package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE ID=?;", id);
    }

    @Override
    public void add(Materiel m) {
        String sql = "INSERT INTO MATERIEL(name, type,stock) VALUES(?, ?,?)";
         this.jdbcTemplate.update(sql, m.getName(),m.getType(),m.getStock());
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM materiel WHERE id=?";
        this.jdbcTemplate.update(query, id);
    }

    @Override
    public void edit(int id, String newName,int stock) {
        String query = "UPDATE materiel SET name=?,stock=? WHERE id=?";
        this.jdbcTemplate.update(query,newName,stock,id);
    }

    @Override
    public int getItemStock(int id) {
        String sql = "SELECT stock FROM MATERIEL WHERE ID=?;";
        int stock = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return stock;
    }

    @Override
    public int getItemAllocated(int id) {
        String sql = "SELECT allocated FROM MATERIEL WHERE ID=?;";
        int num = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return num;
    }

    @Override
    public Boolean isAllocated(int id) {
        Materiel materiel = findOne(id);
        if(materiel!=null){
            return materiel.getAllocated()>0;
        }
        else return false ;
    }

    @Override
    public Boolean isAvailable(int id) {
        String sql = "SELECT available FROM MATERIEL WHERE ID=?;";
        String available = jdbcTemplate.queryForObject(sql, String.class, id);
        if("yes".equalsIgnoreCase(available)) return true;
        else return false ;
    }

    @Override
    public void editAvailable(int id,String change) {
        String query = "UPDATE materiel SET available=? WHERE id=?";
        this.jdbcTemplate.update(query,change,id);

    }


    @Override
    public void decreaseStock(int id) {
        int stock = getItemStock(id);
        int allocated = getItemAllocated(id);
        String query = "UPDATE materiel SET stock=? , allocated=? WHERE id=?";
        this.jdbcTemplate.update(query,--stock,++allocated,id);
    }

    @Override
    public void increaseStock(int id) {
        int stock = getItemStock(id);
        int allocated = getItemAllocated(id);
        String query = "UPDATE materiel SET stock=? , allocated=? WHERE id=?";
        this.jdbcTemplate.update(query,++stock,--allocated,id);
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
