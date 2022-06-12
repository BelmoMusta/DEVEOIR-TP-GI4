package com.ensa.gi4.datatabase.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ensa.gi4.datatabase.api.MaterielDao;
import com.ensa.gi4.datatabase.api.UtilisateurDao;
import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.modele.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurDaoImpl extends GenericDAO<Utilisateur> implements UtilisateurDao {
	 String userName;
	 String password ;
	 String password1; 
	    boolean isValide;
	    int roleInt;
	    String role;
	    JdbcTemplate jdbcTemplate;
	  public UtilisateurDaoImpl(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate=jdbcTemplate;
	  }

	@Override
	public Utilisateur find_user() {
		while(true){
		
		isValide = false;
	            Scanner scanner = new Scanner(System.in);
	            System.out.println("username : ");
	            userName = scanner.next();
	            System.out.println("password : ");
	            password = scanner.next();
	            password=doHashing(password);
	          


	            try {
	            	String sql="SELECT * FROM UTILISATEUR WHERE username=?;";
	                Utilisateur user = super.TrouverModeDePasse(sql, userName);
	                String vrai_code = user.getPassword();
	                if (isValide == false) {
	                	
	                	
	                    if (vrai_code.equals(password)) {
	                    	isValide = true;
	                        return user;
	                    } else {
	                        System.out.println("Oops !!! password incorrect");
	                    }
	                } else {
	                    if (vrai_code.equals(password)) {
	                        return user;
	                    } else {
	                        System.out.println("Oops !!! password incorrect");
	                    }
	                }

	                return null;
	            }catch(Exception e){
	                System.out.println("user not found");
	                System.out.println("1- pour essayer une nouvelle fois");
	                System.out.println("0- pour sortir de l'application");
	                String next = scanner.next();
	                if ("0".equals(next)) {
	                    System.exit(0);
	                } else if ("1".equals(next)) {
	                    continue;
	                }
	            }
		}
	}

    
	
	  public String doHashing(String password)
	    {
	        try{
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            messageDigest.update(password.getBytes());
	            
	            byte[] resultByteArray = messageDigest.digest();
	            StringBuilder sbuilder = new StringBuilder();
	            for(byte b : resultByteArray)
	            {
	            	sbuilder.append(String.format("%02x",b));
	            }
	            return sbuilder.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        return "";
	    }



	@Override
	protected RowMapper<Utilisateur> getRowMapper() {
		// TODO Auto-generated method stub
		return new UserRowMapper();
	}



	@Override
	public void creeUtilisateur() {
		
		while(true) {
		isValide = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("new username : ");
        userName = scanner.next();
        System.out.println("password : ");
        password = scanner.next();
        password=doHashing(password);
        System.out.println("role (tapper 1 pour admin et 2 pour user)  ");
        roleInt = Integer.parseInt(scanner.next());
        if(roleInt == 1) {
        	role = "admin";
        	
        }else if(roleInt == 2) {
        	role = "user";
        	
        }else {
        	System.out.println("tapper 1 ou 2");
        	
        }


        try {
        	String sql="INSERT INTO  UTILISATEUR(username,Password,role) VALUES (?,?,?);";
          
        	super.EXECree(sql,userName,password,role);
        	break;
        	
        }catch(Exception e){
            System.out.println("or lord de creation utilisateur repeter!!!!!(sign up)");
            System.out.println("1- pour essayer une nouvelle fois de CREE UTILISATEUR");
            System.out.println("2- connexion(sign in)");
            System.out.println("0- pour sortir de l'application");
            String next = scanner.next();
            if ("0".equals(next)) {
                System.exit(0);
            } else if ("1".equals(next)) {
                continue;
            }
        }
		
	}
	}



	
}
