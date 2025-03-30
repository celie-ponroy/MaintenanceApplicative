package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    protected final int frequenceJours;

    public EventPeriodique(TitreEvent title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvent dureeMinutes, int frequenceJours) {
        super(EventType.PERIODIQUE, title, proprietaire, dateDebut, dureeMinutes);
        //verificaiton frequence
        if(frequenceJours < 0) {
            throw new IllegalArgumentException("La fréquence doit être positive");
        }
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours "+" (id: "+ eventId+ ")";

    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime occurrence = this.dateDebut;
        while (!occurrence.isAfter(fin)) {
            LocalDateTime finOccurrence = occurrence.plusMinutes(this.getDureeMinutes().minutes());

            if (occurrence.isBefore(fin) && finOccurrence.isAfter(debut)) {
                return true; // conflit
            }
            occurrence = occurrence.plusDays(frequenceJours);
        }
        return false;//sans conflit
    }


    public int getFrequenceJours() {
        return frequenceJours;
    }
}
