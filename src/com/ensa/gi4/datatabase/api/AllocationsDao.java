package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocations;

import java.util.List;

public interface AllocationsDao {
    List<Allocations> findAll();

    List<Allocations> findMaterielAllocatedByUserID(int userId);

    Allocations findMaterielAllocatedByMaterielID(int materielId);

    Allocations findMaterielAllocatedByUserIDAndMaterielID(int userId, int materielId);
}
