package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Chaise;
import com.ensa.gi4.modele.Livre;
import com.ensa.gi4.modele.Materiel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
	 @Override
	    public void onApplicationEvent(MyEvent<T> event) {

	        if(event.getEventType().toString().equals("ADD"))
	        {
	            if(event.getSource() instanceof Livre)
	            {
	                System.out.println("Votre livre est ajouté avec succés!");
	            }
	            else if(event.getSource() instanceof Chaise)
	            {
	                System.out.println("Votre chaise est ajouté avec succés!");
	            }
	        }
	        if(event.getEventType().toString().equals("UPDATE"))
	        {
	            if(event.getSource() instanceof Livre)
	            {
	                System.out.println("Votre livre est modifié avec succés");
	            }
	            else if(event.getSource() instanceof Chaise)
	            {
	                System.out.println("Votre chaise est modifié avec succés");
	            }
	        }

	    }
}
