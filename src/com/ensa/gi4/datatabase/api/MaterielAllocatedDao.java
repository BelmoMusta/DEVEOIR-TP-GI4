package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Allocation;

import java.util.List;

public interface MaterielAllocatedDao {
    List<Allocation> getAllAllocations();

    List<Allocation> getAllocationsByUser(int userID);

    Allocation ifMaterielIsAllocatedByUser(int userID, int materielID);

    Allocation getAllocationByMateriel(int materielID);

}
