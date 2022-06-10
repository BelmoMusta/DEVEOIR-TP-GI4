package com.ensa.gi4.database.impl.MaterialDao;

import com.ensa.gi4.database.api.MaterialDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaterialDaoImpl extends GenericDAO<Material> implements MaterialDao {

    @Override
    public List<Material> findAll() {
        return super.findAll("SELECT * FROM MATERIAL;");
    }

    @Override
    public Material findOne(Long id) {
        return super.findOne("SELECT * FROM MATERIAL WHERE id=?;", id);
    }

    @Override
    public List<Material> findMaterialByType(Long id) {
        String sql = "SELECT * FROM material WHERE material_type=?";
        return super.jdbcTemplate.query(sql, this.getRowMapper(), id);
    }

    @Override
    public int createNewMaterial(Material material) {
        String sql = "INSERT INTO material(name, material_type, time_rented, available, stock) VALUES(?, ?, ?, ?, ?)";
        return this.jdbcTemplate.update(sql, material.getName(), material.getMaterialType().getId(),
                material.getTimeRented(), material.isAvailable(), material.getStock());
    }

    @Override
    public int deleteMaterial(Long id) {
        String sql = "DELETE FROM material WHERE id=?";
        return this.jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateOne(Material material) {
        String sql = "UPDATE material SET name=?, material_type=?, time_rented=?, stock=?, available=? WHERE id=?";
        return this.jdbcTemplate.update(sql, material.getName(), material.getMaterialType().getId(),
                material.getTimeRented(), material.getStock(), material.isAvailable(), material.getId());
    }

    @Override
    protected MaterialRowMapper getRowMapper() { // template method design pattern
        return new MaterialRowMapper();
    }
}
