package org.example;

import java.time.LocalDateTime;

public class EventRDVPersonnel extends Event{
    public EventRDVPersonnel( String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super("RDV_PERSONNEL", title, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}
