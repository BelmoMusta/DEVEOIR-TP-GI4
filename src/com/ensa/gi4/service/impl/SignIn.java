package com.ensa.gi4.service.impl;

        import com.ensa.gi4.datatabase.impl.GenericDAO;
        import com.ensa.gi4.datatabase.impl.UserRowMapper;
        import com.ensa.gi4.modele.User;
        import com.ensa.gi4.service.api.ISignIn;
        import org.springframework.jdbc.core.RowMapper;
        import org.springframework.stereotype.Component;

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.Scanner;

@Component
//@Aspect
public class SignIn extends GenericDAO<User> implements ISignIn {

    String username;
    String password;
    boolean tmp ;

    //    @Before("execution(* com.ensa.gi4.controller.GestionMaterielController.afficherMenu())")
    public User signInPerson()
    {

        while(true)
        {
            tmp = false;
            Scanner scanner = new Scanner(System.in);
            System.out.println("username : ");
            username = scanner.next();
            System.out.println("password : ");
            password = scanner.next();
            password = hashMDP(password);

            try{
                User person = super.findRealPassword("SELECT * FROM USER WHERE username=?;", username);
                String realPassword = person.getPassword();
                if(tmp==false){
                    realPassword = hashMDP(realPassword);
                    if(realPassword.equals(password))
                    {
                        tmp=true;
                        return person;
                    }
                    else
                    {
                        System.out.println("password incorrect");
                    }
                }
                else{
                    if(realPassword.equals(password))
                    {
                        return person;
                    }
                    else
                    {
                        System.out.println("password incorrect");
                    }
                }

            }
            catch(Exception e){
                System.out.println("user not found");
                System.out.println("1- pour essayer une nouvelle fois, entrer 1");
                System.out.println("0- pour sortir de l'application, entrer 0");
                String next = scanner.next();
                if ("0".equals(next)) {
                    System.exit(0);
                } else if ("1".equals(next)) {
                    continue;
                }
            }
        }

    }

    public User signUpPerson()
    {

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("username : ");
            username = scanner.next();
            System.out.println("password : ");
            password = scanner.next();
            System.out.println(password);
            if(tmp==false)
            {
                password = hashMDP(password);
                tmp = true;
            }
            super.ajouterUser("INSERT INTO USER(username,password,role) VALUES (?,?,'user')", username,password);
            return signInPerson();

        }
    }
    //Methode pour hasher le mot de passe
    public String hashMDP(String password)
    {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : resultByteArray)
            {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    protected RowMapper<User> getRowMapper() {
        return new UserRowMapper();
    }
}
