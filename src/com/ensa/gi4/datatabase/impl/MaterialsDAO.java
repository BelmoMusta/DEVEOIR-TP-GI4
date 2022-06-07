package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.modele.Material;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("materialsDao")
public class MaterialsDAO implements DAO<Material>, InitializingBean {
    @Autowired
    MaterielRowMapper rowMapper;

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public List<Material> getAll() {
        String sql = "SELECT * FROM materials";

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Optional<Material> getById(int id) {
        String sql = "SELECT * FROM materials WHERE material_id = ?";
        Material material = null;
        try {
            material = jdbcTemplate.queryForObject(sql, rowMapper,id);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(material);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM materials WHERE material_id = ?";
        if (jdbcTemplate.update(sql, id) == 1)
            System.out.println("Material deleted");
    }

    @Override
    public void update(int id, Material material) {
        String sql = "UPDATE materials SET name = ?, quantity = ?, material_type= ? WHERE material_id = ?";
        if (jdbcTemplate.update(sql, material.getName(), material.getQuantity(), material.getMaterial_type(), id) == 1)
            System.out.println("Material updated : " + material.getName());
    }

    @Override
    public void add(Material material) {
        String sql = "INSERT INTO materials(name, quantity, material_type) VALUES (?, ?, ?)";
        if (jdbcTemplate.update(sql, material.getName(), material.getQuantity(), material.getMaterial_type()) == 1)
            System.out.println("Material created : " + material.getName());
    }


}
