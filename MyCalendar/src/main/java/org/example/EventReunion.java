package org.example;

import java.time.LocalDateTime;

public class EventReunion extends Event {
    public String lieu; // utilisé seulement pour REUNION
    public String participants;


    public EventReunion( String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
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
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}
