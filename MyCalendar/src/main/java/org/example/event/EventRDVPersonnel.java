package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;

public class EventRDVPersonnel extends Event{
    public EventRDVPersonnel(TitreEvent title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvent dureeMinutes) {
        super(EventType.RDV_PERSONNEL, title, proprietaire, dateDebut, dureeMinutes);

    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString()+" (id: "+ eventId+ ")";
    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }


}
