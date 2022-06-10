package com.ensa.gi4.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ensa.gi4.datatabase.api.MaterielAllouerDao;
import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UserDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.MaterielAllouer;
import com.ensa.gi4.modele.TypeMateriel;
import com.ensa.gi4.modele.User;
import com.ensa.gi4.service.api.AllocationMaterielService;

@Service
public class AllocationMaterielServiceImpl implements AllocationMaterielService {
	
	@Autowired
	@Qualifier("materielDaoImpl")
	MaterielDao materielDao; 
	@Autowired
	@Qualifier("materielAllouerDaoImpl")
	MaterielAllouerDao materielAllouerDao;
	@Autowired
	@Qualifier("userDaoImpl")
	UserDao userDao;
	
	@Value("${string.allocationMaterielServiceImpl.allocationSuccess}")
	private String allocationSuccess; 
	
	@Value("${string.allocationMaterielServiceImpl.allocationFailed}")
	private String allocationFailed;
	
	@Value("${string.allocationMaterielServiceImpl.rendreMaterielAlloueSuccess}")
	private String rendreMaterielAlloueSuccess;
	
	@Value("${string.allocationMaterielServiceImpl.rendreMaterielAlloueFailed}")
	private String rendreMaterielAlloueFailed;
	
	@Value("${string.allocationMaterielServiceImpl.listeMaterielAlloueParUserSuccess}")
	private String listeMaterielAlloueParUserSuccess;
	
	@Value("${string.allocationMaterielServiceImpl.listeMaterielAlloueParUserFailed}")
	private String listeMaterielAlloueParUserFailed;
	
	@Value("${string.allocationMaterielServiceImpl.listeMaterielAlloueParUser.user}")
	private String userMateriel;
	@Value("${string.allocationMaterielServiceImpl.allocationEmpty}")
	private String allocationEmpty;


	@Override
	public Optional<Materiel> chercherMateriel(TypeMateriel typeMateriel ,String code) {
		return materielDao.findOne(code, typeMateriel.toString());
	}

	@Override
	public int allouerMateriel(TypeMateriel typeMateriel, String code, User user) {
		int test =  materielDao.allouerMateriel(code, typeMateriel.toString(), user); 
		
		if(test == 0 ) {
			System.out.println("----------------------------------------------------");
			System.out.println(allocationFailed);
			System.out.println("----------------------------------------------------");
		}else if (test == -1) {
			System.out.println("----------------------------------------------------");
			System.out.println(allocationEmpty);
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println(allocationSuccess);
			System.out.println("----------------------------------------------------");
		}
		
		return test; 
			
		
	}

	@Override
	public int rendreMaterielAlloue(TypeMateriel typeMateriel, String code, User user) {
		int test =  materielDao.rendreMateriel(code, typeMateriel.toString(), user); 
		
		if (test == 0) {
			System.out.println("----------------------------------------------------");
			System.out.println(rendreMaterielAlloueFailed);
			System.out.println("----------------------------------------------------");
		}else if (test == -1) {
			System.out.println("----------------------------------------------------");
			System.out.println(allocationEmpty);
			System.out.println("----------------------------------------------------");
		}else {
			System.out.println("----------------------------------------------------");
			System.out.println(rendreMaterielAlloueSuccess);
			System.out.println("----------------------------------------------------");
		}
		
		return test;
	}

	@Override
	public Optional<List<Materiel>> listeMaterielAlloue(User user) {
			
		return materielDao.listeMaterielAlloue(user); 
	}

	@Override
	public Optional<List<Materiel>> listeMateriel() {
		return materielDao.findAll();
	}

	@Override
	public void listeMaterielAlloueParUser() {
		Optional<List<User>> checkUsers = userDao.findAll(); 
		Optional<List<MaterielAllouer>> checkMaterielAllouers =  materielAllouerDao.listeMaterielAlloueParUser(); 
		
		System.out.println("------------\t"+ listeMaterielAlloueParUserSuccess +"\t------------");
		System.out.println("----------------------------------------------------");
		if (checkMaterielAllouers.isPresent() && checkUsers.isPresent()) {
			for (User user : checkUsers.get()) {
				System.out.println(userMateriel + " : "+ user);
				for (MaterielAllouer materielAllouer : checkMaterielAllouers.get()) {
					if (user.getName().equals(materielAllouer.getUser().getName()) && user.getRole() == materielAllouer.getUser().getRole()) {
						System.out.println(materielAllouer.getMateriel());
					}
				}
				
				System.out.println("----------------------------------------------------");
			}
		}
		else {
			System.out.println("\t" + listeMaterielAlloueParUserFailed);
		}
		
	}

}
