package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.List;

public class GestionLivreServiceImpl implements GestionMaterielService, SmartInitializingSingleton{
    MaterielDao materielDao;
    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public List<Materiel> findAll() {
        return materielDao.findAllDAO();
    }

    @Override
    public Materiel findOne(Long id) {
        return materielDao.findOneDAO(id);
    }

    @Override
    public void addMaterial(Materiel materiel) {
        materielDao.addMaterialDAO(materiel.getName(),materiel.getCode(),materiel.getStock(),materiel.isDisponibility());
    }

    @Override
    public void deleteMaterial(Long id) {
        materielDao.deleteMaterialDAO(id);
    }

    @Override
    public void updateMaterial(String name,String  code,Integer stock,Long id) {
        materielDao.updateMaterialDAO(name,code,stock,id);
    }

    @Override
    public void updateMaterial(Materiel materiel) {

    }

    public void makeMaterialUnavailable(Long id) {

    }
    @Override
    public void afterSingletonsInstantiated() {

    }

    /* @Override
     public void listerMateriel() {
         System.out.println("Liste de matériel :\n 3 Livres \n 4 chaises");
     }

     @Override
     public void ajouterNouveauMateriel(Materiel materiel) {

         System.out.println("L'ajout du matériel " + materiel.getName() + " effectué avec succès !");
     }*/

}
