package com.ensa.gi4.listeners;

import com.ensa.gi4.datatabase.api.DAO;
import com.ensa.gi4.modele.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener<T> implements ApplicationListener<MyEvent<T>> {
    @Autowired
    DAO<Material> materialsDAO;
    @Override
    public void onApplicationEvent(MyEvent<T> event) {
        EventType eventType = event.getEventType();

        switch (eventType)
        {
            case ADD:
                Material eventSource = (Material) event.getSource();

                materialsDAO.add(eventSource);
                break;
            case REMOVE:
                Integer id = (Integer) event.getSource();
                materialsDAO.delete(id);
                break;
            default:
        }
    }
}
