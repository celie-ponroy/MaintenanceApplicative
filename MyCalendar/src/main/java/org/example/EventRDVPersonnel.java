package org.example;

import java.time.LocalDateTime;

public class EventRDVPersonnel extends Event{
    public EventRDVPersonnel( String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(EventType.RDV_PERSONNEL, title, proprietaire, dateDebut, dureeMinutes);

    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}
