package com.ensa.gi4.datatabase.api;
import com.ensa.gi4.modele.MaterielAllocated;
import java.util.List;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

public interface MaterielAllocatedDAO {
	List<MaterielAllocated> findAlloueByUser(int userId);
    List<MaterielAllocated> findAlloueAll();	
    MaterielAllocated findMaterielUser(int userId, int materielId);
}
