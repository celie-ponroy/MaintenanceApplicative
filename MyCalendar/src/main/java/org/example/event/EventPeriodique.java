package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;
import java.util.Objects;

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
            System.out.println("Vérification occurrence : " + occurrence + " -> " + finOccurrence);
            if (occurrence.isBefore(fin) && finOccurrence.isAfter(debut)) {
                System.out.println("Conflit détecté !");
                return true;
            }
            occurrence = occurrence.plusDays(frequenceJours);
        }
        System.out.println("Aucun conflit détecté");
        return false;
    }



    public int getFrequenceJours() {
        return frequenceJours;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EventPeriodique that = (EventPeriodique) o;
        return frequenceJours == that.frequenceJours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), frequenceJours);
    }
}
