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

		if (event.getEventType().toString().equals("ADD")) {
			refactorCode(event, "ajout�");

		} else if (event.getEventType().toString().equals("UPDATE")) {
			refactorCode(event, "modifi�");

		} else if (event.getEventType().toString().equals("REMOVE")) {
			refactorCode(event, "supprim�");
		}
	}

	private void refactorCode(MyEvent<T> event, String e) {
		if (event.getSource() instanceof Livre) {
			System.out.println("Votre livre a �t� bien " + e);

		} else if (event.getSource() instanceof Chaise) {
			System.out.println("Votre chaise a �t� bien " + e);
		}
		System.out.println(event.getSource());
	}
}
