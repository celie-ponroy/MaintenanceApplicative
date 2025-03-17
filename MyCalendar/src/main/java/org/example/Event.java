package org.example;

import java.time.LocalDateTime;

public abstract class Event {
    public String type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public String title;
    public String proprietaire;
    public LocalDateTime dateDebut;
    public int dureeMinutes;

    public Event(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.type = type;
        this.title = title;
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
}