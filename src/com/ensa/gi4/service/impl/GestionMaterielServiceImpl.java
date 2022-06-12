package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.RentedDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import com.ensa.gi4.service.api.SignInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("gestionMaterielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {

    @Autowired
    @Qualifier("materielDao")
    MaterielDao materielDao;
    @Autowired
    RentedDao rentedDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SignInUserService signInUserService;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public List<Materiel> listMaterial() {
        return materielDao.findAll();
    }

    @Override
    public int addNewMaterial(Materiel materiel) {
        System.out.println("L'ajout du matériel " + materiel.getName() + " est en cours... .");
        return materielDao.createNewMaterial(materiel);
    }

    @Override
    public Materiel searchMaterialByName(String name) {
        return materielDao.findMaterielByName(name);
    }

    @Override
    public Materiel seachMaterialById(Long id) {
        return materielDao.findOne(id);
    }

    @Override
    public int deleteMaterial(Materiel materiel) {
    return materielDao.deleteMaterial(materiel.getId());
    }

    @Override
    public int editMateriel(Materiel materiel,String name) {
    return materielDao.editMaterial(materiel.getId(),name);
    }

    @Override
    public void rentMateriel(Materiel materiel,User user) {

        materielDao.rentMateriel(materiel,user);
    }

    @Override
    public void returnMateriel(Materiel materiel,User user) {
        materielDao.putBackMateriel(materiel,user);
    }

    @Override
    public void markUnavailable(Materiel materiel) {
        materielDao.markUnavailable(materiel);
    }

    //each user can see its own rented materials
    @Override
    public List<RentedMaterial> viewRentedMaterielsByEachUser(User user) {

        return rentedDao.viewRentedMaterialsByEachUser(user);
    }

    //Admin privilege
    @Override
    public List<RentedMaterial> viewRentedOfAllUsers() {
        return rentedDao.viewRentedMaterialsOfAllUsers();
    }

    @Override
    public int checkRentedBeforeRenting(User user) {
        return rentedDao.checkNumberRentedMaterial(user);
    }

    @Override
    public User searchUserById(Long id) {
        return userDao.findOne(id);
    }

}
