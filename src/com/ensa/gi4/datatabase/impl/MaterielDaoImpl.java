package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    public Materiel getMaterielModifié() {
        return materielModifié;
    }

    public void setMaterielModifié(Materiel materielModifié) {
        this.materielModifié = materielModifié;
    }

    public Materiel getMaterielSupprimé() {
        return materielSupprimé;
    }

    public void setMaterielSupprimé(Materiel materielSupprimé) {
        this.materielSupprimé = materielSupprimé;
    }

    private Materiel materielModifié;
    private Materiel materielSupprimé;

    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findMateriel(Long id) {
        try{
            return super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", id);
        }
        catch (Exception e){
            System.out.println("Materiel Non trouvé");
        }

        return null;
    }

    @Override
    public void addMateriel(Materiel materiel) {
       if(materiel instanceof Livre)
           super.addOne("INSERT INTO MATERIEL(materielName,materielCode,materielType,isDisponible,isAllocated) VALUES(?,?,?,?,?);",materiel.getMaterielName(),materiel.getMaterielCode(),"livre",true,false);
       else if(materiel instanceof Chaise)
           super.addOne("INSERT INTO MATERIEL(materielName,materielCode,materielType,isDisponible,isAllocated) VALUES(?,?,?,?,?);",materiel.getMaterielName(),materiel.getMaterielCode(),"chaise",true,false);

    }

    @Override
    public void supprimerMateriel(int id) {
        try{
            Materiel materiel = super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", (long) id);

            if(materiel.getMaterielType().equals("livre"))
            {
                materiel = new Livre();
            }
            else
            {
                materiel = new Chaise();
            }
            setMaterielSupprimé(materiel);
            super.deleteOne("DELETE FROM MATERIEL WHERE idMateriel=?;", id);
        }
        catch (Exception e){
            System.out.println("Id non trouvé");
        }

    }
    @Override
    public void modifierMateriel(int id) {
    try {
        Materiel materiel = super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", (long) id);

        if(materiel.getMaterielType().equals("livre"))
        {
            materiel = new Livre();
        }
        else
        {
            materiel = new Chaise();
        }
        setMaterielModifié(materiel);

        System.out.println("1- pour modifier le nom , entrer 1");
        System.out.println("2- pour modifier le code, entrer 2");
        System.out.println("3- pour modifier le type, entrer 3");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("entrer le nouveau nom/code/type :");
        Scanner scanner1 = new Scanner(System.in);
        String nouveau = scanner1.next();
        if ("1".equals(next)) {
            super.modifyOne("UPDATE MATERIEL SET materielName = ? WHERE idMateriel=?;",nouveau,id);
        } else if ("2".equals(next)) {
            super.modifyOne("UPDATE MATERIEL SET materielCode = ? WHERE idMateriel=?;",nouveau,id);
        }else if ("3".equals(next)) {
            super.modifyOne("UPDATE MATERIEL SET materielType = ? WHERE idMateriel=?;",nouveau,id);
        }else{
            System.out.println("Entrez un choix valid.");
        }
    }catch (Exception e){
        System.out.println("id non trouvé");
    }

    }

    @Override
    public String rendreIndispo(int id) {
        try{
            Boolean bool = super.findBool("SELECT isDisponible FROM MATERIEL WHERE idMateriel=?;",id);
            if(bool){
                super.makeUnavailable("UPDATE MATERIEL SET isDisponible = 'false' WHERE idMateriel=?;",id);
                return "Materiel indisponible";
            }
            else return "Materiel deja est indisponible";
        }catch (Exception e){
            System.out.println("id non trouvé");
        }
        return "";
    }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }
}
