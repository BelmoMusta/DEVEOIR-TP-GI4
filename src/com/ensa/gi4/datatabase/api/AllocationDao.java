package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;

import java.util.List;

public interface AllocationDao {
    List<Allocation> findAll();
    List<Allocation> findAllPerUser(Long idUser);

}
