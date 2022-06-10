package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface AllocationDAO {
    void allocateMaterialDAO(Long mid,Long uid);
    void returnMaterialDAO(Long id);
    List<Allocation> findMaterialAllocatedDAO();
    List<Allocation> findMaterialAllocatedDAO(Long id);
}
