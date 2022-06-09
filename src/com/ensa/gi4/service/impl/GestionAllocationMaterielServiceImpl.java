package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielAlloueDao;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionAllocationMaterielService;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("allocationService")
public class GestionAllocationMaterielServiceImpl implements GestionAllocationMaterielService {

    @Autowired
    MaterielAlloueDao materielAlloueDao;

    @Override
    public void allouerMateriel(User user, Long idMateriel) {

        System.out.println(materielAlloueDao.allocateMateriel(user, idMateriel));

    }

    @Override
    public void rendreMaterielAlloue(User user, Long idMateriel) {

        System.out.println(materielAlloueDao.ReturnAllocatedMateriel(user, idMateriel));

    }

    public void listerMaterielAllouePourUser(User user) {

        System.out.println(materielAlloueDao.findAllAllocatedMaterialsForUser(user));

    }




}
