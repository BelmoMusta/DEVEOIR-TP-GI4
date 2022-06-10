package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImp extends GenericDAO<User> implements UserDao {
    private User user = new User();

    @Override
    protected UserRowMapper getRowMapper() {
        return new UserRowMapper();
    }

    @Override
    public User login(String name, String password) {
        try {
            return super.findAllUser("SELECT * FROM USER WHERE userName=? and password=?;",name,password);
        }
       catch (Exception e){
           System.out.println("login échoué");
           System.exit(0);
       }
        return null;
    }

    @Override
    public void register(String name,String password) {
        super.addUser("INSERT INTO USER(userName,password,role) values(?,?,'user');",name,password);
    }

    @Override
    public String allouer(int idUs,String name,int qte,int nb_days) {
            try {
                int id = super.findIdByName("SELECT idMateriel FROM MATERIEL WHERE materielName=?;",name);
                Boolean dispo = super.findBool("SELECT isDisponible FROM MATERIEL WHERE idMateriel=?;",id);
                if(dispo){
                    super.modifyAllocation("UPDATE MATERIEL SET isAllocated = 'true' WHERE materielName=?;",name);
                    super.addMatAll("INSERT INTO materielAllocated(idUser,idMateriel,quantity,nb_days) VALUES (?,?,?,?);",idUs,id,qte,nb_days);
                    return "materiel alloué";
                }
                else return "Materiel Non disponible";
            }catch (Exception e){
                System.out.println("Nom du materiel n'existe pas");
            }
            return "";
    }

    @Override
    public String rendre(String name) {
             try{
                 int id = super.findIdByName("SELECT idMateriel From MATERIEL WHERE materielName=?;",name);
                 try {
                     int idu = super.findIdById("SELECT idMateriel From materielAllocated WHERE idMateriel=?;",id);
                     super.modifyAllocationId("UPDATE MATERIEL SET isAllocated = 'false' WHERE idMateriel=?;",id);
                     super.deleteOne("DELETE FROM materielAllocated WHERE idMateriel=?;",idu);
                     return "Materiel rendu";
                 }catch (Exception e){
                     System.out.println("Materiel non existant dans la liste des materiels alloués");
                 }
             }catch (Exception e){
                 System.out.println("Materiel non existant dans la liste des materiels ");
             }
            return "";
    }
}
