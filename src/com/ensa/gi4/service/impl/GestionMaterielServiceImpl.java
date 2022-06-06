package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.ChaiseDaoImpl;
import com.ensa.gi4.datatabase.impl.LivreDaoImpl;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Autowired
    LivreDaoImpl livreDao ;

    @Autowired
    ChaiseDaoImpl chaiseDao ;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public void showAll() {
        List<Materiel> list = this.materielDao.findAll();
        for(Materiel m : list){
            System.out.println("---------------");
            System.out.println("Id : "+m.getId());
            System.out.println("Name : "+m.getName());
            if(m.getAllocated()) System.out.println("Allocated : Yes");
            else System.out.println("Allocated : No");
        }
    }

    @Override
    public void addNew(Materiel materiel) {

    }


}
