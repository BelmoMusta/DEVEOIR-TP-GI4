package com.ensa.gi4.datatabase.api;
import com.ensa.gi4.modele.Material;
import com.ensa.gi4.modele.User;
import java.util.List;

public interface MaterielDao {


    List<Material> findAll();

    Material findOne(Long materialId);
    int Create(Material material);
    int Delete(Long materialId);
    int Edit(Long materialId, String name, String materialType);
    int Return(long materialId);
    int Allocate( long materialId, long userId);
    int MarkNotAvailable(long materialId);
    List<Material>  ListAllocatedMaterial();
    List<Material> ListAllocatedMaterialUser(long userId);
}
