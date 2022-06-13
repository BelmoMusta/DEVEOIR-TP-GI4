/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocationDAO;
import com.ensa.gi4.modele.Allocation;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AllocationDAOImpl extends GenericDAO<Allocation> implements AllocationDAO{

    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return new AllocationRowMapper();
    }

    @Override
    public List<Allocation> findSome(int idM) {
        return super.findSome("SELECT * FROM allocations WHERE idM=? AND rendu=?;", idM, false);
    }

    @Override
    public int insertAllocation(Allocation allocation) {
        return super.addEntity("INSERT INTO allocations (qta, rendu, idM,idU) VALUES (?,?,?,?);", allocation.getQta(), allocation.getRendu(), allocation.getIdM(), allocation.getIdU());
    }

    @Override
    public Allocation estAllouer(int idM, int idU) {
        return super.Exist("select * from allocations WHERE idU=? AND idM=?;", idU, idM);
        
    }

    @Override
    public void updateAllocation(Allocation alloc) {
        super.addEntity("UPDATE allocations SET qta=?, rendu=? WHERE idM=? AND idU=?;",alloc.getQta(), alloc.getRendu(), alloc.getIdM(), alloc.getIdU());
    }

    @Override
    public List<Allocation> getMyAllocations(int idU) {
        return super.findSome("SELECT * FROM allocations WHERE idU=? AND rendu=?;", idU, false);
    }

   
    
}
