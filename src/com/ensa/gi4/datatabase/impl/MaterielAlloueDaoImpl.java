package com.ensa.gi4.datatabase.impl;


import com.ensa.gi4.datatabase.api.MaterielAlloueDao;
import com.ensa.gi4.modele.MaterielAlloue;
import com.ensa.gi4.modele.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MaterielAlloueDaoImpl extends GenericDAO<MaterielAlloue> implements MaterielAlloueDao {

    @Override
    public List<MaterielAlloue> findAllAllocatedMaterialsForUser(User user) {

        String query = "SELECT * FROM ALLOCATION WHERE IDUSER=?";
        return super.findAllAllocatedMaterialsForUser(query, user.getUserId());

    }

    @Override public String allocateMateriel(User user, Long idMateriel) {

        // remplir la tables allocation
        String query1 = "INSERT INTO ALLOCATION(IDUSER, IDMAT, ALLOCATIONDURATION) VALUES(?,?,CURRENT_TIMESTAMP)";
        super.allocateOne(query1, user.getUserId(), idMateriel);

        // modifier la table materiel
        String query2 = "UPDATE MATERIEL SET STOCK = STOCK - 1 WHERE ID = ?";
        super.resetTheStock(query2, idMateriel);


        return "L'allocation du materiel a passé veuillez verifier.";


    }

   @Override
    public String ReturnAllocatedMateriel(User user, Long idMateriel) {

        String query1 = "DELETE FROM ALLOCATION WHERE IDMAT = ?";
        super.deleteReturnedMaterial(query1, idMateriel);

        String query2 = "UPDATE MATERIEL SET STOCK = STOCK + 1 WHERE ID = ?";
        super.resetTheStock(query2, idMateriel);

        return "Le retour du materiel a passé avec succes veuillez verifier";


    }


    @Override
    protected RowMapper<MaterielAlloue> getRowMapper() {
        return new MaterielAlloueRowMapper();
    }
}
