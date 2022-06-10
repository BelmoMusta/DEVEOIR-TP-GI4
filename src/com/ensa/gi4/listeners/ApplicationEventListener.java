package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Materiel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
	
	@Value("${string.gestionMaterielServiceImpl.addMaterielSuccess}")
	private String addMaterielSuccess; 
	
	@Value("${string.gestionMaterielServiceImpl.modifierMaterielSuccess}")
	private String modifierMaterielSuccess; 
	
    @Override
    public void onApplicationEvent(MyEvent<T> event) {
 
        switch (event.getEventType()) {
		case ADD:
			System.out.println(addMaterielSuccess);
			break;
			
		case UPDATE:
			System.out.println(modifierMaterielSuccess);
			break;
		default:
			break;
		}
        
        System.out.println(event.getSource());
        
    }
}
