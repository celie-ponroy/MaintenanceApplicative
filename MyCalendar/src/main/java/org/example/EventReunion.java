package org.example;

import java.time.LocalDateTime;

public class EventReunion extends Event {
    public String lieu; // utilisé seulement pour REUNION
    public String participants;


    public EventReunion( String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        super("REUNION", title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}
