package com.ensa.gi4.listeners;

import com.ensa.gi4.modele.Materiel;
import com.ensa.gi4.service.api.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationEventListener<T extends Materiel> implements ApplicationListener<MyEvent<T>> {
    final I18nService i18nService;

    @Override
    public void onApplicationEvent(MyEvent<T> event) {
        Materiel materiel= (Materiel) event.getSource();
        switch (event.getEventType()){
            case ADD -> System.out.println(this.i18nService.getFormattedText("message.success.add", materiel.getType(), materiel.getId()));
            case REMOVE -> System.out.println(this.i18nService.getFormattedText("message.materiel.deleted", materiel.getId()));
            case UPDATE -> System.out.println(this.i18nService.getFormattedText("message.materiel.updated", materiel.toString()));
        }
    }
}
