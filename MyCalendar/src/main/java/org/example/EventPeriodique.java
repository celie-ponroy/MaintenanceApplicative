package org.example;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    public int frequenceJours; // uniquement pour PERIODIQUE

    public EventPeriodique(  String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super("PERIODIQUE", title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";

    }
}
