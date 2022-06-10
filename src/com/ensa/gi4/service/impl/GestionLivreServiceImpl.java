package com.ensa.gi4.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.listeners.ApplicationPublisher;
import com.ensa.gi4.listeners.EventType;
import com.ensa.gi4.listeners.MyEvent;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.service.api.GestionMaterielService;

@Service("livreService")
public class GestionLivreServiceImpl implements GestionMaterielService {

	@Autowired
	@Qualifier("materielDaoImpl")
	MaterielDao materielDao;
	@Autowired
	@Qualifier("applicationPublisher")
	ApplicationPublisher publisher;
	
	@Value("${string.gestionMaterielServiceImpl.addMaterielSuccess}")
	private String addMaterielSuccess; 
	
	@Value("${string.gestionMaterielServiceImpl.addMaterielFailed}")
	private String addMaterielFailed; 
	
	@Value("${string.gestionMaterielServiceImpl.deleteMaterielSuccess}")
	private String deleteMaterielSuccess; 
	
	@Value("${string.gestionMaterielServiceImpl.deleteMaterielFailed}")
	private String deleteMaterielFailed; 
	
	@Value("${string.gestionMaterielServiceImpl.modifierMaterielSuccess}")
	private String modifierMaterielSuccess; 
	
	@Value("${string.gestionMaterielServiceImpl.modifierMaterielFailed}")
	private String modifierMaterielFailed; 
	
	@Value("${string.gestionMaterielServiceImpl.materielIndisponibleSuccess}")
	private String materielIndisponibleSuccess; 
	
	@Value("${string.gestionMaterielServiceImpl.materielIndisponibleFailed}")
	private String materielIndisponibleFailed; 
	
	@Value("${string.gestionMaterielServiceImpl.materielIndisponibleEmpty}")
	private String materielIndisponibleEmpty;
	
	@Value("${string.gestionMaterielServiceImpl.deleteEmptyMateriel}")
	private String deleteEmptyMateriel; 
	
	@Value("${string.gestionMaterielServiceImpl.modifierMaterielEmpty}")
	private String modifierMaterielEmpty;
	
	@Override
	public int ajouterNouveauMateriel(Materiel materiel) {
		int test =  materielDao.ajouter(materiel); 
		
		if (test != 1) {
			System.out.println("----------------------------------------------------");
			System.out.println(addMaterielFailed);
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			publisher.publish(new MyEvent<Materiel>(materiel, EventType.ADD));
			System.out.println("----------------------------------------------------");
		}
		
		return test; 
	}

	@Override
	public int supprimerMateriel(String code) {
		int test =  materielDao.supprimerMateriel(code);
		
		if (test == 0) {
			System.out.println("----------------------------------------------------");
			System.out.println(deleteMaterielFailed);
			System.out.println("----------------------------------------------------");
		}else if (test == -1) {
			System.out.println("----------------------------------------------------");
			System.out.println(deleteEmptyMateriel);
			System.out.println("----------------------------------------------------");
		}
		else {
			System.out.println("----------------------------------------------------");
			System.out.println(deleteMaterielSuccess);
			System.out.println("----------------------------------------------------");
		}
		
		return test;
	}


	@Override
	public int modifierMateriel(String code, Integer stock, String ancienCode) {
		int test =  materielDao.updateMateriel(code, stock, ancienCode); 
		if (test == 0) {
			System.out.println("----------------------------------------------------");
			System.out.println(modifierMaterielFailed);
			System.out.println("----------------------------------------------------");
		}else if (test == -1) {
			System.out.println("----------------------------------------------------");
			System.out.println(modifierMaterielEmpty);
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			Optional<Materiel>  checkMaterielOptional = materielDao.findOne(code, TypeMateriel.LIVRE.toString());
			publisher.publish(new MyEvent<Materiel>(checkMaterielOptional.get(), EventType.UPDATE));
			System.out.println("----------------------------------------------------");
		}
		
		return test; 
	}

	@Override
	public int materielIndisponible(String code) {
		int test =  materielDao.materielIndisponible(code); 
		
		if (test == 0) {
			System.out.println("----------------------------------------------------");
			System.out.println(materielIndisponibleFailed);
			System.out.println("----------------------------------------------------");
		}else if (test == -1) {
			System.out.println("----------------------------------------------------");
			System.out.println(materielIndisponibleEmpty);
			System.out.println("----------------------------------------------------");
		} else {
			System.out.println("----------------------------------------------------");
			System.out.println(materielIndisponibleSuccess);
			System.out.println("----------------------------------------------------");
		}
		
		return test; 
		
	}

 
}
