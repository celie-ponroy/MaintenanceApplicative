package org.example;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    protected final int frequenceJours;

    public EventPeriodique(  String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(EventType.PERIODIQUE, title, proprietaire, dateDebut, dureeMinutes);
        //verificaiton frequence
        if(frequenceJours < 0) {
            throw new IllegalArgumentException("La fréquence doit être positive");
        }
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";

    }
    @Override
    public boolean isInPeriod(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime occurrence = this.dateDebut;
        while (!occurrence.isAfter(fin)) {
            LocalDateTime finOccurrence = occurrence.plusMinutes(this.getDureeMinutes());

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
