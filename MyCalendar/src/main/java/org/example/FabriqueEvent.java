package org.example;

import java.time.LocalDateTime;

public class FabriqueEvent {
    public static Event getEventRDV(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        try {
            return new EventRDVPersonnel(title, proprietaire, dateDebut, dureeMinutes);
        }catch (Exception e) {
            return null;
        }
    }
    public static Event getEventReunion(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        try {
            return new EventReunion(title,proprietaire,dateDebut,dureeMinutes,lieu,participants);
        }catch (Exception e) {
            return null;
        }
    }
    public static Event getEventPeriodique(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        try{
            return new EventPeriodique(title,proprietaire,dateDebut,dureeMinutes,frequenceJours);
        }catch (Exception e) {
            return null;
        }
    }
}
