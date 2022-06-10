package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {


    public List<Materiel> findAllDAO(){
        String sql="SELECT * FROM MATERIEL";
        return super.findAll(sql);
    }

    public Materiel findOneDAO(Long id){
        String query1="SELECT * FROM MATERIEL WHERE ID=?";
        return super.findOne(query1,id);
    }
    /*public void allocateMaterialDAO(Long mid,Long uid){
        List<Materiel> list= findMaterialAllocatedDAO(uid);
        String alloc=mid.toString();
        for(Materiel mat:list){

            alloc+= mat.getId();;
        }
        String query="""
        UPDATE USERS
        SET ALLOCATED = ?
        WHERE ID = ?""";
        super.allocateMaterialDAO(query,alloc,uid);
    }
    public void returnMaterialDAO(Long id){

    }
    public List<Materiel> findMaterialAllocatedDAO(){
        return null;
    }
    public List<Materiel> findMaterialAllocatedDAO(Long id){
        String query="SELECT * FROM USERS WHERE ID= ?";
        return super.findMaterialAllocatedDAO(query,id);
    }*/
    //Materiel rechercheParIdDAO(String code);
    public void addMaterialDAO(String name,String code,Integer stock,Boolean disponibility){
        String sql="INSERT INTO  MATERIEL (NAME,CODE,STOCK,DISPONIBILITY) VALUES (?,?,?,?)";
        super.addMaterialDAO(sql,name,code,stock,disponibility);
    }
    public void deleteMaterialDAO(Long id){
        String sql="DELETE FROM MATERIEL WHERE ID= ?";
        super.deleteMaterialDAO(sql,id);
    }
    public void updateMaterialDAO(String name,String code,Integer stock,Long id){
        String query="""
        UPDATE MATERIEL
        SET NAME = ?, CODE= ? ,STOCK= ?
        WHERE ID = ?""";
        Materiel materiel= findOneDAO(id);
        if(code.equals("no")){
            code=materiel.getCode();
        }
        if(stock.equals(-1)){
            stock=materiel.getStock();
        }
        if(name.equals("no")){
            name=materiel.getName();
        }
        super.updateMaterialDAO(query,name,code,stock,id);
    }


    public void updateMaterialDAO(Materiel materiel){
        String query="""
        UPDATE MATERIEL
        SET NAME = ?, CODE= ? ,STOCK= ?
        WHERE ID = ?""";
        Materiel mat= findOneDAO(materiel.getId());
        if(materiel.getCode().equals("no")){
            materiel.setCode(mat.getCode());
        }
        if(materiel.getStock().equals(-1)){
            materiel.setStock(mat.getStock());
        }
        if(materiel.getName().equals("no")){
            materiel.setName(mat.getName());
        }
        if(materiel.getStock().equals(0)){
            materiel.setDisponibility(false);
        }else{
            materiel.setDisponibility(true);
        }
        super.updateMaterialDAO(query,materiel);
    }
    @Override
    public void makeItUnavailable(Long id) {
        String query1="""
        UPDATE MATERIEL
        SET STOCK= 0
        WHERE ID = ?""";
        String query2="""
        UPDATE MATERIEL
        SET disponibility =false
        WHERE ID = ?""";
        Integer value=super.cheeck(query1,id);
        if(value>0){
            super.makeItUnavailable(query2,id);
        }
        else{
            System.out.println("L'id que vous avez saisis ne correspond à aucun matériel!!!");
        }
        //super.makeItUnavailable(query1,id);

    }

    @Override
    protected RowMapper<Materiel> getRowMapper() {
        return new MaterielRowMapper();
    }
}
