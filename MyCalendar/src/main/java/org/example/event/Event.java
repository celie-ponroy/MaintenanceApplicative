package org.example.event;

import org.example.Utilisateur;

import java.time.LocalDateTime;
import java.util.Objects;
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
    public int getDureeMinutesInt(){return dureeMinutes.minutes();}

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return type == event.type && Objects.equals(title, event.title) && Objects.equals(eventId, event.eventId) && Objects.equals(proprietaire, event.proprietaire) && Objects.equals(dateDebut, event.dateDebut) && Objects.equals(dureeMinutes, event.dureeMinutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, eventId, proprietaire, dateDebut, dureeMinutes);
    }
}