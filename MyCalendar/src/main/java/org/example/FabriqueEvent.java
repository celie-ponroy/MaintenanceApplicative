package org.example;

import java.time.LocalDateTime;

public class FabriqueEvent {
    static Event getEventRDV(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        return new EventRDVPersonnel(title,proprietaire,dateDebut,dureeMinutes);
    }
    static Event getEventReunion(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        return new EventReunion(title,proprietaire,dateDebut,dureeMinutes,lieu,participants);
    }
    static Event getEventPeriodique(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        return new EventPeriodique(title,proprietaire,dateDebut,dureeMinutes,frequenceJours);
    }
}
