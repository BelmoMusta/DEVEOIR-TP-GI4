package com.ensa.gi4.datatabase.api;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAlloue;
import com.ensa.gi4.modele.User;

import java.util.List;

public interface MaterielAlloueDao {



    String allocateMateriel(User user, Long idMaterialToAllocate);

    String ReturnAllocatedMateriel(User user, Long idMateriel);

    List<MaterielAlloue> findAllAllocatedMaterialsForUser(User user);



}
