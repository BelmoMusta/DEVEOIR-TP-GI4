package com.ensa.gi4.datatabase.api;

import java.util.List;

import com.ensa.gi4.modele.Allocation;

public interface AllocationDao {
	List<Allocation> listeMaterielAlloue(int id);
	List<Allocation> listeMaterielAlloueAll();

}
