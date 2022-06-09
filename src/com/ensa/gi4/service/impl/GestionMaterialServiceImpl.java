package com.ensa.gi4.service.impl;

import com.ensa.gi4.database.api.MaterialDao;
import com.ensa.gi4.database.api.MaterialTypeDao;
import com.ensa.gi4.database.api.RentedMaterialDao;
import com.ensa.gi4.database.api.UserDao;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.MaterialType;
import com.ensa.gi4.modele.RentedMaterial;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.GestionMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("materialService")
public class GestionMaterialServiceImpl implements GestionMaterialService {

    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private MaterialTypeDao materialTypeDao;
    @Autowired
    private RentedMaterialDao rentedMaterialDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Material> showMaterialList() {
        List<Material> materialList = this.materialDao.findAll();
        for (Material material: materialList) {
            this.setMaterialType(material);
        }
        return materialList;
    }

    @Override
    public Material searchMaterialById(Long id) {
        Material material = this.materialDao.findOne(id);
        if (material == null) {
            return null;
        }
        this.setMaterialType(material);
        return material;
    }

    @Override
    public List<Material> searchMaterialByType(Long materialTypeId) {
        List<Material> searchedMaterials = this.materialDao.findMaterialByType(materialTypeId);
        for (Material material: searchedMaterials) {
            this.setMaterialType(material);
        }
        return searchedMaterials;
    }

    public void setMaterialType(Material material) {
        MaterialType materialType = this.materialTypeDao.findOne(material.getMaterialType().getId());
        material.setMaterialType(materialType);
    }

    @Override
    public List<MaterialType> getAllMaterialType() {
        return this.materialTypeDao.findAll();
    }

    @Override
    public Long addNewMaterialType(String name) {
        return materialTypeDao.createNewMaterialType(name);
    }

    @Override
    public int addNewMaterial(Material material) {
        return materialDao.createNewMaterial(material);
    }

    @Override
    public int deleteMaterial(Long materialIdToBeDeleted) {
        Material materialToBeDeleted = materialDao.findOne(materialIdToBeDeleted);
        if (materialToBeDeleted == null) {
            return 0;
        } else if (isAtLeastOneMaterialIsRented(materialToBeDeleted)) {
            return -1;
        }
        return materialDao.deleteMaterial(materialIdToBeDeleted);
    }

    @Override
    public int editMaterial(Material materialToBeEdited) {
        return materialDao.updateOne(materialToBeEdited);
    }

    @Override
    public boolean isAtLeastOneMaterialIsRented(Material material) {
        return material.getTimeRented() > 0;
    }


    public boolean canMaterialBeRented(Material material) {
        return material.getTimeRented() == material.getStock();
    }

    @Override
    public int rentOneMaterial(RentedMaterial materialRentRecord) {
        int rowAffected = rentedMaterialDao.insertOne(materialRentRecord);
        if (rowAffected != 0) {
            Material material = materialRentRecord.getMaterial();
            this.updateTimeRented(material, material.getTimeRented() + 1);
        }
        return rowAffected;
    }

    @Override
    public int returnRentedMaterial(Long returnedMaterialId) {
        RentedMaterial rentedMaterial = rentedMaterialDao.findOne(returnedMaterialId);
        if (rentedMaterial == null) {
            return 0;
        }
        Material material = materialDao.findOne(rentedMaterial.getMaterial().getId());
        this.updateTimeRented(material, material.getTimeRented() - 1);
        return rentedMaterialDao.deleteOne(returnedMaterialId);
    }

    public void updateTimeRented(Material material, int newValue) {
        material.setTimeRented(newValue);
        materialDao.updateOne(material);
    }

    @Override
    public List<RentedMaterial> getUserRentedMaterials(Long userId) {
        List<RentedMaterial> rentedMaterials = rentedMaterialDao.findMaterialRentedByUser(userId);
        for (RentedMaterial rentedMaterial: rentedMaterials) {
            Material material = materialDao.findOne(rentedMaterial.getMaterial().getId());
            this.setMaterialType(material);
            rentedMaterial.setMaterial(material);
        }
        return rentedMaterials;
    }

    @Override
    public int makeMaterialAvailableOrUnavailable(Material material) {
        material.setAvailable(!material.isAvailable());
        return materialDao.updateOne(material);
    }

    @Override
    public List<User> getRentedMaterialsByUsers() {
        List<User> usersThatRentedMaterials = new ArrayList<>();
        List<Long> userIds = rentedMaterialDao.findAllUserIds();
        for (Long userId: userIds) {
            User user = userDao.findOne(userId);
            List<RentedMaterial> rentedMaterials = this.getUserRentedMaterials(userId);
            user.setRentedMaterials(rentedMaterials);
            usersThatRentedMaterials.add(user);
        }
        return usersThatRentedMaterials;
    }

    @Override
    public Integer getNumberOfMaterialToReturnToday(Long userId) {
        return rentedMaterialDao.findUserRentedMaterialDueToday(userId);
    }


}
