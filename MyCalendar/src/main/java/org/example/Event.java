package org.example;

import java.time.LocalDateTime;

public abstract class Event {
    protected EventType type;
    protected String title;
    protected String proprietaire;
    protected LocalDateTime dateDebut;
    protected int dureeMinutes;

    public Event(EventType type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.type = type;
        this.title = title;
        if(proprietaire==null||proprietaire.isEmpty()) {
            throw new IllegalArgumentException("Proprietaire non valide");
        }
        this.proprietaire = proprietaire;
        //verifications date debut
        if (dateDebut.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La date de début doit être dans le futur");
        }
        this.dateDebut = dateDebut;
        //verifications duree
        if (dureeMinutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive");
        }
        this.dureeMinutes = dureeMinutes;

    }

    public abstract String description();

    /**
     * renvoi faux si la date en paramettre rentre en conflit avec la date de this
     * @param debut
     * @param fin
     * @return
     */
    public abstract boolean isInPeriod(LocalDateTime debut, LocalDateTime fin);


    public EventType getType() {
        return type;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public String getTitle() {
        return title;
    }
}