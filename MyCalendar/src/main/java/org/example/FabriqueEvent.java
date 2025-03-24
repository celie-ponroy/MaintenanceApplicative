package org.example;

import java.time.LocalDateTime;

public class FabriqueEvent {
    public static EventRDVPersonnel getEventRDV(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        try {
            return new EventRDVPersonnel(title, proprietaire, dateDebut, dureeMinutes);
        }catch (Exception e) {
            return null;
        }
    }
    public static EventReunion getEventReunion(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        try {
            return new EventReunion(title,proprietaire,dateDebut,dureeMinutes,lieu,participants);
        }catch (Exception e) {
            return null;
        }
    }
    public static EventPeriodique getEventPeriodique(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        try{
            return new EventPeriodique(title,proprietaire,dateDebut,dureeMinutes,frequenceJours);
        }catch (Exception e) {
            return null;
        }
    }
    public static EventSouscription getEventSouscription(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, double prix, String entreprise) {
        try {
            return new EventSouscription(title,proprietaire,dateDebut,dureeMinutes,prix,entreprise);
        }catch (Exception e) {
            return null;
        }
    }
}
