package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.AllocationDAO;
import com.ensa.gi4.modele.Allocation;
import com.ensa.gi4.modele.Materiel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Repository
public class AllocationDAOImpl  extends GenericDAO<Allocation> implements AllocationDAO {
    public void returnMaterialDAO(Long id){
        String query1="DELETE FROM ALLOCATION WHERE MATERIALID= ?";
        String query2="""
        UPDATE MATERIEL
        SET STOCK= STOCK+1
        WHERE ID = ?""";

        int numberOfChangedRows= super.getReturnMaterialDAOChange(query1,id);
        System.out.println(numberOfChangedRows);
        if(numberOfChangedRows>0) {
            super.returnMaterialDAO(query2,id);
            System.out.println("Le matériel a été rendu avec succé,Merci!!");
        }else{
            System.out.println("Vous pouvez pas rendre se matériel!!");
        }
    }
    public List<Allocation> findMaterialAllocatedDAO(){
        String query="SELECT * FROM ALLOCATION";
        return super.findMaterialAllocatedDAO(query);
    }
    @Override
    public void allocateMaterialDAO(Long uid, Long mid) {
        String query1="INSERT INTO ALLOCATION (userid,materialid,date) VALUES (?,?,?)";
        String query2="""
        UPDATE MATERIEL
        SET STOCK= STOCK-1
        WHERE ID = ? AND STOCK!= 0""";
        Date date= new Date();
        int numberOfChangedRows=super.allocateMaterialDAO(query2, mid);
        if(numberOfChangedRows>0) {
            super.allocateMaterialDAO(query1, uid, mid, date);
            //super.allocateMaterialDAO(query2, mid);
            System.out.println("allocation with success");
        }
        else {
            System.out.println("Désolé,vous pouvez pas allouez ce matériel!!");
        }
    }

    @Override
    public List<Allocation> findMaterialAllocatedDAO(Long id) {
        String sql="SELECT * FROM ALLOCATION WHERE userid= ?";
        return super.findMaterialAllocatedDAO(sql,id);
    }
    @Override
    protected RowMapper<Allocation> getRowMapper() {
        return new AllocationRowMapper();
    }

}
