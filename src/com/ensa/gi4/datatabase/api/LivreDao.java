package com.ensa.gi4.datatabase.api;



import com.ensa.gi4.modele.Livre;
import java.util.List;

public interface LivreDao {
    List<Livre> findAll();
    Livre findOne(Integer id);
    Livre save(Livre livre);

    Integer findMaxId();
}
