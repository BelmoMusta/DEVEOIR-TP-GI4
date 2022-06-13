package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Material> implements MaterielDao {
    @Override
    public List<Material> findAll() {
        return super.findAll("SELECT * FROM MATERIAL;");
    }

    @Override
    public Material findOne(Long materialId) {
        return super.findOne("SELECT * FROM MATERIAL WHERE ID=?;", materialId);
    }

    @Override
    public int Create(Material material) {
         String query = "INSERT INTO MATERIAL(MATERIALID,NAME,MATERIALTYPE,ISAVAILABLE) VALUES(?, ?, ?, ?)";
        return this.jdbcTemplate.update(query,material.getMaterialId(),material.getName(),material.getMaterialType(),"TRUE");
    }

    @Override
    public int Delete(Long materialId) {
        String query=  "DELETE FROM MATERIAL WHERE MATERIALID=?";
        return this.jdbcTemplate.update(query,materialId);
    }

    @Override
    public int Edit(Long materialId, String name, String materialType) {
        String query = "UPDATE MATERIAL SET NAME=?, MATERIALTYPE=? WHERE MATERIALID=?";
        return this.jdbcTemplate.update(query,name,materialType);
    }

    @Override
    public int Return(long materialId) {
        String query = "UPDATE MATERIAL ISAVAILABLE=TRUE WHERE MATERIALID=?";
        return this.jdbcTemplate.update(query,materialId);
    }

    @Override
    public int Allocate( long materialId, long userId) {
        String query="UPDATE MATERIAL SET ISAVAILABLE=FALSE, USERID=? WHERE MATERIALID=?";
        return this.jdbcTemplate.update(query,userId,materialId);


    }

    @Override
    public int MarkNotAvailable(long materialId) {
        String query="UPDATE MATERIAL SET ISAVAILABLE=FALSE WHERE MATERIALID=?";
                return this.jdbcTemplate.update(query,materialId);}

    @Override
    public List<Material> ListAllocatedMaterial() {
        return super.findAll("SELECT * FROM MATERIAL WHERE ISAVAILABLE=FALSE;");
    }

    @Override
    public List<Material> ListAllocatedMaterialUser(long userId) {
        return super.findAll("SELECT * FROM MATERIEL WHERE ISAVAILABLE=FALSE AND USERID="+userId+";");
    }



    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }}

