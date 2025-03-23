package org.example;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    protected int frequenceJours;

    public EventPeriodique(  String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
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
            if (!occurrence.isBefore(debut)) {
                return true;  // Si l'occurrence est dans la période
            }
            occurrence = occurrence.plusDays(frequenceJours);  // Passage à la prochaine occurrence
        }
        return false;
    }

    public int getFrequenceJours() {
        return frequenceJours;
    }
}
