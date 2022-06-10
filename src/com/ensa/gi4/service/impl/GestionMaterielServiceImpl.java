package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.ChaiseDao;
import com.ensa.gi4.datatabase.api.LivreDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.impl.AllocatedItemDaoImpl;
import com.ensa.gi4.datatabase.impl.ChaiseDaoImpl;
import com.ensa.gi4.datatabase.impl.LivreDaoImpl;
import com.ensa.gi4.modele.AllocatedItem;
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

    @Autowired
    AllocatedItemDaoImpl allocatedItemDao;

    @Override
    public void init() {
        System.out.println("inititialisation du service");
    }

    @Override
    public Materiel findOneById(int id) {
        Materiel m = this.materielDao.findOne(id);
        if(m ==null) return null ;
        return m;
    }

    @Override
    public List<Materiel> showAll() {
        List<Materiel> list = this.materielDao.findAll();
        return list ;
    }


    @Override
    public void addNew(Materiel materiel) {
        this.materielDao.add(materiel);
    }

    @Override
    public void editMateriel(Materiel materiel) {
        this.materielDao.edit(materiel.getId(),materiel.getName(),materiel.getStock());
    }

    @Override
    public boolean deleteMateriel(Materiel materiel) {
        if(this.materielDao.isAllocated(materiel.getId())){
            return false;
        } else {
        this.materielDao.delete(materiel.getId());
        return true ;
        }
    }

    @Override
    public Boolean toAllocate(int id_materiel,int id_user) {
        int stock = this.materielDao.getItemStock(id_materiel);
        if(stock>0 && isAvailable(id_materiel)){
            this.materielDao.decreaseStock(id_materiel);
            this.allocatedItemDao.addItem(id_user,id_materiel);
            return true ;
        }else return false ;
    }

    @Override
    public Boolean toReturn(int id_materiel,int id_user) {
        int allocated = this.materielDao.getItemAllocated(id_materiel);
        if(allocated>0){
            this.materielDao.increaseStock(id_materiel);
            this.allocatedItemDao.deleteItem(id_user,id_materiel);
            return true;
        }
        return false;
    }

    @Override
    public void editAvailable(int id,String change) {
        this.materielDao.editAvailable(id,change);
    }

    @Override
    public Boolean isAvailable(int id) {
        return this.materielDao.isAvailable(id);
    }

    @Override
    public List<AllocatedItem> showAllAllocatedItems() {
        List<AllocatedItem> list = this.allocatedItemDao.findAll();
        if(list.isEmpty()) return null ;
        return list ;
    }

    @Override
    public List<AllocatedItem> showUserAllocatedItems(int id_user) {
        List<AllocatedItem> list =this.allocatedItemDao.findByUser(id_user);
        if(list.isEmpty()) return null ;
        return list ;
    }


}
