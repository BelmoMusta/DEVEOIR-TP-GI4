package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AllocationsDAO implements DAO<Allocation>, InitializingBean {

    @Autowired
    AllocationRowMapper rowMapper;

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Allocation> getAll() {
        String sql = "SELECT * FROM allocations";

        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Optional<Allocation> getById(int id) {
        String sql = "SELECT * FROM allocations WHERE allocation_id = ?";
        Allocation allocation = null;
        try {
            allocation = jdbcTemplate.queryForObject(sql, rowMapper,id);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(allocation);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM allocations WHERE allocation_id = ?";
        if (jdbcTemplate.update(sql, id) == 1)
            System.out.println("Material returned");
    }

    @Override
    public void update(int id, Allocation allocation) {
        String sql = "UPDATE allocations SET user_id = ?, material_id = ?, duration = ? WHERE allocation_id = ?";
        if (jdbcTemplate.update(sql, allocation.getUser_id(), allocation.getMaterial_id(), allocation.getDuration(), id) == 1)
            System.out.println("Allocation updated");
    }

    @Override
    public void add(Allocation allocation) {
        String sql = "INSERT INTO allocations(user_id, material_id, duration) VALUES (?, ?, ?)";
        if (jdbcTemplate.update(sql, allocation.getUser_id(), allocation.getMaterial_id(), allocation.getDuration()) == 1)
            System.out.println("Allocation Added");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
