package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class FabriqueEvent {
    public static EventRDVPersonnel getEventRDV(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        try {
            return new EventRDVPersonnel(new TitreEvent(title), proprietaire, dateDebut, new DureeEvent(dureeMinutes));
        }catch (Exception e) {
            return null;
        }
    }
    public static EventReunion getEventReunion(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, Set<Utilisateur> participants) {
        try {
            return new EventReunion(new TitreEvent(title),proprietaire,dateDebut,
                    new DureeEvent(dureeMinutes),lieu,participants);
        }catch (Exception e) {
            return null;
        }
    }
    public static EventPeriodique getEventPeriodique(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        try{
            return new EventPeriodique(new TitreEvent(title),proprietaire,dateDebut,new DureeEvent(dureeMinutes),frequenceJours);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static EventSouscription getEventSouscription(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, double prix, String entreprise) {
        try {
            return new EventSouscription(new TitreEvent(title),proprietaire,dateDebut,new DureeEvent(dureeMinutes),prix,entreprise);
        }catch (Exception e) {
            return null;
        }
    }
}
