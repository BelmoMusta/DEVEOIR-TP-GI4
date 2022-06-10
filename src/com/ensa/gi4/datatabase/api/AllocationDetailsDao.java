package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.AllocationDetails;

import java.util.List;

public interface AllocationDetailsDao {
    List<AllocationDetails> findAll();
    AllocationDetails findOneById(Long id);
    List<AllocationDetails> findAllByUser(Long userId);
    List<AllocationDetails> findAllByMateriel(Long materielId);
    void save(AllocationDetails allocationDetails);
    void deleteAllByMaterialId(Integer id);
}
