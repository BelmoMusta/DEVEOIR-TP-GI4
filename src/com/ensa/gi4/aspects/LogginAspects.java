package com.ensa.gi4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LogginAspects {

    Logger logger = Logger.getLogger(LogginAspects.class.getName());

    public LogginAspects() throws Exception{
        logger.addHandler(new FileHandler("log.xml"));
        logger.setUseParentHandlers(false);
    }


    long t1,t2;

    @Pointcut("execution(* com.ensa.gi4.controller.GestionMaterielController.afficherMenu(..))")
    public void pc1(){

    }




    @Around("pc1()")
    public Object autour(ProceedingJoinPoint p, JoinPoint j) throws Throwable {
        t1 = System.currentTimeMillis();
        logger.info("Avant l'execution de la methode " + j.getSignature());

        Object obj =    p.proceed();
        logger.info("Apres l'execution de la methode " + j.getSignature());
        t2 = System.currentTimeMillis();
        logger.info("Durée d'exécution de la méthode "+ (t2-t1));

        return obj;
    }


//    @Before("pc1()")
//    public void avant(JoinPoint joinPoint){
//        t1 = System.currentTimeMillis();
//        System.out.println("Avant l'execution de la methode " + joinPoint.getSignature());
//    }
//
//    @After("pc1()")
//    public void apres(JoinPoint joinPoint){
//        System.out.println("Apres l'execution de la methode " + joinPoint.getSignature());
////        t2 = System.currentTimeMillis();
//        System.out.println("Durée d'exécution de la méthode "+ (t2-t1));
//    }


}
