package com.ensa.gi4.datatabase.impl;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.User;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

//@Repository
@Component()
public class MaterielDaoImpl extends GenericDAO<Materiel> implements MaterielDao {
    @Override
    public List<Materiel> findAll() {
        return super.findAll("SELECT * FROM MATERIEL;");
    }

    @Override
    public Materiel findOne(int id) {
        return super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;",id);
        }

    @Override
    protected MaterielRowMapper getRowMapper() { // template method design pattern
        return new MaterielRowMapper();
    }

	@Override
	public void ajouterMateriel(Materiel materiel) {
		 super.addMaterial("INSERT INTO MATERIEL(name,code,type,isFree,isAllocate) VALUES (?,?,?,?,?)",materiel.getName(), materiel.getCode(),materiel.getType(),true,false);
    }
		
	
	
	@Override
	public void allouerMateriel(int  idMateriel, int idUser) {
		modifierDisponibilité(idMateriel);
		 ajouterMaterielUser(idMateriel,idUser);
		
	}



	@Override
	public void ajouterMaterielUser(int  idMateriel, int idUser) {
		 super.userAddMaterial("INSERT INTO user_allocate_material(idUser,idMateriel) VALUES (?,?)",idMateriel,idUser);
		
	}

	@Override
	public void supprimerMateriel(int id) {
		try{
            Materiel materiel = super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", id);

            /*if(materiel.getMaterielType().equals("livre"))
            {
                materiel = new Livre();
            }
            else
            {
                materiel = new Chaise();
            }*/
      
            super.deleteMaterial("DELETE FROM MATERIEL WHERE idMateriel=?;", id);
        }
        catch (Exception e){
            System.out.println("Materiel non trouvé");
        }
	}

	@Override
	public void modifierMateriel(int id) {
		try {
	        Materiel materiel = super.findOne("SELECT * FROM MATERIEL WHERE idMateriel=?;", id);


	        System.out.println("1- pour modifier le nom , entrer 1");
	        System.out.println("2- pour modifier le code, entrer 2");
	        System.out.println("3- pour modifier le type, entrer 3");
	        Scanner scanner = new Scanner(System.in);
	        String next = scanner.next();
	        System.out.println("entrer le nouveau nom , code , type :");
	        Scanner scanner1 = new Scanner(System.in);
	        String newOne = scanner1.next();
	        if ("1".equals(next)) {
	            super.modifierMateriel("UPDATE MATERIEL SET name = ? WHERE idMateriel=?;",newOne,id);
	        } else if ("2".equals(next)) {
	            super.modifierMateriel("UPDATE MATERIEL SET code = ? WHERE idMateriel=?;",newOne,id);
	        }else if ("3".equals(next)) {
	            super.modifierMateriel("UPDATE MATERIEL SET type = ? WHERE idMateriel=?;",newOne,id);
	        }else{
	            System.out.println("Entrez un choix valid.");
	        }
	    }catch (Exception e){
	        System.out.println("Materiel non trouvé");
	    }

	    }

	@Override
	public void rendreMateriel(int id) {
	
		try {
		        
		            super.deleteMaterial("DELETE FROM user_allocate_material WHERE idMateriel=?",id);
		}
		        catch (Exception e){
		            System.out.println(" Id non trouvé ");
		        }
		
	}

	@Override
	public void modifierDisponibilité(int id) {
		try {
		 super.modifierDisponibilité("UPDATE MATERIEL set isFree = 'false' where idMateriel = ?",id);}
		catch (Exception e) {
			 System.out.println("Id non trouvé");
		}
		
	
	}

	@Override
	public void modifierAllocation(int id) {
		try {
		 super.modifierAllocation("UPDATE MATERIEL set isAllocate = ? idMateriel",id);}
		catch (Exception e) {
			 System.out.println("Id non trouvé");
		}
	}

	

	
	}
		
	

	
	
	
	


