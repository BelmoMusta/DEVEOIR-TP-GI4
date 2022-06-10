package com.ensa.gi4.service.impl;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    public MaterielDao materielDao;

    GestionMaterielServiceImpl(MaterielDao materielDAO) {
        this.materielDao = materielDAO;

    }
    GestionMaterielServiceImpl() {
    }
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public List<Materiel> findAll(){
        return materielDao.findAllDAO();
    }
    @Override
    public Materiel findOne(Long id){
        return materielDao.findOneDAO(id);
    }

    @Override
    public void addMaterial(Materiel materiel){
        materielDao.addMaterialDAO(materiel.getName(),materiel.getCode(),materiel.getStock(),materiel.isDisponibility());
    }
    @Override
    public void deleteMaterial(Long id){
        materielDao.deleteMaterialDAO(id);
    }
    @Override
    public void updateMaterial(String name,String code,Integer stock,Long id){
        materielDao.updateMaterialDAO(name,code,stock,id);
    }
    @Override
    public void updateMaterial(Materiel materiel){
        materielDao.updateMaterialDAO(materiel);
    }
    public void makeMaterialUnavailable(Long id) {
        materielDao.makeItUnavailable(id);
    }










}
