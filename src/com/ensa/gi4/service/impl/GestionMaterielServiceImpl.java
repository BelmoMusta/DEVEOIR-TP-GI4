package com.ensa.gi4.service.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("materielService")
public class GestionMaterielServiceImpl implements GestionMaterielService {
    @Autowired
    MaterielDao materielDao;

    @Override
    public void init() {
        System.out.println("Service Initialization");
    }

    @Override
    public void AddNewMaterial(Material material) {

        System.out.println(material.getName() + " Successfully added !");
    }

    @Override
    public void SearchMaterial(long materialId) {
        System.out.println(materielDao.findOne(materialId));

    }

    @Override
    public void EditMaterial(long materialId, String name, String materialType) {
        materielDao.Edit(materialId, name, materialType);
        System.out.println("Done!");

    }

    @Override
    public void ListAllMaterial() {
        List<Material> Material = materielDao.findAll();
        for(Material material:Material)
        {
            System.out.println("Name : "+material.getName()+  " Type : " + material.getMaterialType()+ " Availability : "+ material.isAvailable() );

    }}


    @Override
    public void DeleteMaterial(long materialId) {
        materielDao.Delete(materialId);
        System.out.println("Done!");
    }


    @Override
    public void AllocateMaterial(long materialId, long userId) {
        boolean available = materielDao.findOne(materialId).isAvailable();
        if(available) {
            materielDao.Allocate(materialId,userId);
            System.out.println("Done!");
        }else {
            System.out.println("Material already rented");

    }}

    @Override
    public void ReturnMaterial(long materialId) {
        materielDao.Return(materialId);
        System.out.println("Done!");

    }

    @Override
    public void ListUserAllocatedMaterialToUser(long userId) {
                List<Material> AllocatedMaterialUser = materielDao.ListAllocatedMaterialUser(userId);
        for(Material material:AllocatedMaterialUser)
        {
            System.out.println("Name : "+material.getName()+  " Type : " + material.getMaterialType()+ " Availability : "+ material.isAvailable() );

    }}

    @Override
    public void ListAllocatedMaterial() {
        List<Material> AllocatedMaterial= materielDao.ListAllocatedMaterial();
        for(Material material:AllocatedMaterial)
        {
            System.out.println("Name : "+material.getName()+  " Type : " + material.getMaterialType()+ " Availability : "
                    + material.isAvailable());
    }


}

    public void MarkNotAvailable(long materialId) {
        materielDao.MarkNotAvailable(materialId);
        System.out.println("Done!");
    }
}
