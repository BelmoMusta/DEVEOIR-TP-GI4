package com.ensa.gi4.database.api;

import com.ensa.gi4.modele.RentedMaterial;

import java.util.List;

public interface RentedMaterialDao {
    RentedMaterial findOne(Long id);
    List<RentedMaterial> findAll();
    List<RentedMaterial> findMaterialRentedByUser(Long userId);
    int insertOne(RentedMaterial materialRentRecord);
    int deleteOne(Long returnedMaterialId);
    List<Long> findAllUserIds();
    Integer findUserRentedMaterialDueToday(Long userId);
}
