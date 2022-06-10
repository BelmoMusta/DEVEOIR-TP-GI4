package com.ensa.gi4.service.api;

import com.ensa.gi4.modele.Allocation;

import java.util.List;

public interface AllocationService {
    void allocateMaterial(Long mid,Long uid);
    void returnMaterial(Long id);
    void findMaterialAllocated();
    List<Allocation> findMaterialAllocated(Long id);
}
