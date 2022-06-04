package com.ensa.gi4.security;

import com.ensa.gi4.modele.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Lazy
public class SessionHolder {
    @Value("${session.expiration}")
    private String expiration;
    private User loggedUser;
    private LocalDateTime validity;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

    public boolean isUserExpired(){
        long seconds = ChronoUnit.SECONDS.between(this.validity, LocalDateTime.now());
        return seconds > Long.parseLong(this.expiration);
    }

    public void refreshValidity(){
        this.validity = LocalDateTime.now();
    }
}
