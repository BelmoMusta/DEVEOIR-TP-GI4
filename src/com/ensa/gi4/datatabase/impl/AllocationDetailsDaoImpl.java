package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocationDetailsDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.AllocationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AllocationDetailsDaoImpl extends GenericDAO<AllocationDetails> implements AllocationDetailsDao {
    private final UserDao userDao;
    private final MaterielDao materielDao;

    @Override
    public List<AllocationDetails> findAll() {
        return super.findAll("SELECT * FROM allocation_details;");
    }

    @Override
    public AllocationDetails findOneById(Long id) {
        return super.findOne("SELECT * FROM allocation_details WHERE id=?", id.toString());
    }

    @Override
    public List<AllocationDetails> findAllByUser(Long userId) {
        return this.jdbcTemplate.query("SELECT * FROM allocation_details WHERE user_id=?", getRowMapper(), userId);
    }

    @Override
    public List<AllocationDetails> findAllByMateriel(Long materielId) {
        return this.jdbcTemplate.query("SELECT * FROM allocation_details WHERE materiel_id=?", getRowMapper(), materielId);
    }

    @Override
    protected RowMapper<AllocationDetails> getRowMapper() {
        return new AllocationDetailsRowMapper(userDao, materielDao);
    }
}


record AllocationDetailsRowMapper(UserDao userDao,
                                  MaterielDao materielDao) implements RowMapper<AllocationDetails> {
    @Override
    public AllocationDetails mapRow(ResultSet rs, int i) throws SQLException {
        AllocationDetails allocationDetails = new AllocationDetails();
        allocationDetails.setDate(rs.getDate("date"));
        allocationDetails.setUser(userDao.findOneById(rs.getLong("user_id")));
        allocationDetails.setMateriel(materielDao.findOne(rs.getLong("materiel_id")));
        return allocationDetails;
    }
}