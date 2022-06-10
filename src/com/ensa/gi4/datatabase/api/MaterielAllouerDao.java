package com.ensa.gi4.datatabase.api;

import java.util.List;
import java.util.Optional;

import com.ensa.gi4.modele.MaterielAllouer;

public interface MaterielAllouerDao {

	Optional<List<MaterielAllouer>> listeMaterielAlloueParUser(); 
}
