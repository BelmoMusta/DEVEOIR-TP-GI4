package com.ensa.gi4.database.impl.RentedMaterialDao;

import com.ensa.gi4.database.api.RentedMaterialDao;
import com.ensa.gi4.database.impl.GenericDAO;
import com.ensa.gi4.modele.RentedMaterial;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentedMaterialDaoImpl extends GenericDAO<RentedMaterial> implements RentedMaterialDao {

    @Override
    public RentedMaterial findOne(Long id) {
        String sql = "SELECT * FROM rented_material WHERE id=?";
        return super.findOne(sql, id);
    }

    @Override
    public List<RentedMaterial> findAll() {
        String sql = "SELECT * FROM rented_material";
        return super.findAll(sql);
    }

    @Override
    public List<RentedMaterial> findMaterialRentedByUser(Long userId) {
        String sql = "SELECT * FROM rented_material WHERE user_id=?";
        return this.jdbcTemplate.query(sql, getRowMapper(), userId);
    }

    @Override
    public int insertOne(RentedMaterial materialRentRecord) {
        String sql = "INSERT INTO rented_material(user_id, material_id, deadline) VALUES(?, ?, ?)";
        return this.jdbcTemplate.update(sql, materialRentRecord.getUserId(), materialRentRecord.getMaterial().getId(),
                materialRentRecord.getDeadline());
    }

    @Override
    public int deleteOne(Long returnedMaterialId) {
        String sql = "DELETE FROM rented_material WHERE id=?";
        return this.jdbcTemplate.update(sql, returnedMaterialId);
    }

    @Override
    public List<Long> findAllUserIds() {
        String sql = "SELECT DISTINCT user_id FROM rented_Material";
        return this.jdbcTemplate.queryForList(sql, Long.class);
    }

    @Override
    public Integer findUserRentedMaterialDueToday(Long userId) {
        String sql = "SELECT COUNT(*) FROM rented_material WHERE user_id=? AND deadline=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[] {userId, LocalDate.now()}, Integer.class);
    }

    @Override
    protected RowMapper<RentedMaterial> getRowMapper() {
        return new RentedMaterialRowMapper();
    }
}
