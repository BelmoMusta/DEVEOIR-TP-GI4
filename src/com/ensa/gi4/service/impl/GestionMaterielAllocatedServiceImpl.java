package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.impl.MaterielAllocatedDaoImpl;
import com.ensa.gi4.datatabase.impl.MaterielDaoImpl;
import com.ensa.gi4.service.api.GestionMaterielAllocatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("materielalloueeService")
public class GestionMaterielAllocatedServiceImpl implements GestionMaterielAllocatedService {

    @Autowired
    MaterielAllocatedDaoImpl materielAllocatedDaoImp;

    @Override
    public void listerMaterielAllouees() {
        if(materielAllocatedDaoImp.findAll().size() == 0) System.out.println("Aucun Materiel alloué");
        else System.out.println(materielAllocatedDaoImp.findAll());
    }

    @Override
    public void listerMesMaterielAllouees(int id) {
        if(materielAllocatedDaoImp.listerMesMateriels(id).size() == 0) System.out.println("Vous n'avez pas de materiels alloués");
        else System.out.println(materielAllocatedDaoImp.listerMesMateriels(id));
    }
}
