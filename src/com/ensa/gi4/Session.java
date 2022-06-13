package com.ensa.gi4;

import com.ensa.gi4.modele.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class Session {
    User loggedUser;

    public boolean isLogged(){
        return this.loggedUser != null;
    }
}
