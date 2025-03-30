package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;

public class EventReunion extends Event {
    public final String lieu;
    public String participants;


    public EventReunion(TitreEvent title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvent dureeMinutes, String lieu, String participants) {
        super(EventType.REUNION, title, proprietaire, dateDebut, dureeMinutes);
        if(lieu == null || lieu.isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide");
        }
        this.lieu = lieu;
        if(participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Il doit y avoir au moins un participant");
        }
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants+" (id: "+ eventId+ ")";
    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}
