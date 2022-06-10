package com.ensa.gi4.aspects;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspect {
	
	private static Logger logger = Logger.getLogger(LogAspect.class.getName()); 
	
	@Value("${string.logAspect.logAjoutMateriel}")
	private String addMateriel; 
	@Value("${string.logAspect.logModificationMateriel}")
	private String updateMateriel; 
	@Value("${string.logAspect.logSuppressionMateriel}")
	private String deleteMateriel;
	@Value("${string.logAspect.logMaterielIndisponible}")
	private String materielIndisponible; 
	
	public LogAspect() throws  IOException {
		FileHandler fileHandler = new FileHandler("log.txt", true);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
	}
	
	@AfterReturning(value="execution (* ajouterNouveauMateriel(*) )", returning = "value")
	public void logAjoutMateriel(JoinPoint jp, int value) {
		
		if (value == 1)
			logger.info(addMateriel);
	}
	
	@AfterReturning(value="execution (* supprimerMateriel(*) )", returning = "value")
	public void logModificationMateriel(JoinPoint jp, int value) {	
		if (value == 1)
			logger.info(deleteMateriel);
	
	}
	
	@AfterReturning(value="execution (* modifierMateriel(**) )", returning = "value")
	public void logSuppressionMateriel(JoinPoint jp, int value) {
		if (value == 1)
			logger.info(updateMateriel);
	}
	
	@AfterReturning(value="execution (* materielIndisponible(*) )", returning = "value")
	public void log(JoinPoint jp, int value) {
		if (value == 1)
			logger.info(materielIndisponible);
		
	}

}
