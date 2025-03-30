package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {
    protected EventType type;
    protected final TitreEvent title;
    protected final String eventId;
    protected final Utilisateur proprietaire;
    protected LocalDateTime dateDebut;
    protected final DureeEvent dureeMinutes;

    public Event(EventType type, TitreEvent title, Utilisateur proprietaire, LocalDateTime dateDebut, DureeEvent dureeMinutes) {
        this.type = type;
        this.title = title;
        if(proprietaire==null) {
            throw new IllegalArgumentException("Proprietaire non valide");
        }
        this.proprietaire = proprietaire;
        //verifications date debut
        if (dateDebut.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La date de début doit être dans le futur");
        }
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        eventId = UUID.randomUUID().toString();
    }

    public abstract String description();

    /**
     * renvoi faux si la date en paramettre rentre pas en conflit avec la date de this
     * @param debut
     * @param fin
     * @return
     */
    public abstract boolean isInPeriod(LocalDateTime debut, LocalDateTime fin);


    public EventType getType() {
        return type;
    }

    public DureeEvent getDureeMinutes() {
        return dureeMinutes;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public TitreEvent getTitle() {
        return title;
    }

    public String getEventId() {
        return eventId;
    }
}